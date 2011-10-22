/*
 * Scheduler.java
 * 
 * Copyright (c) 2011 Pablo J. Urbano Santos <flamma at member.fsf.org>. 
 * 
 * This file is part of vpm.
 * 
 * vpm is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * vpm is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with vpm.  If not, see <http ://www.gnu.org/licenses/>.
 */

package com.asqueados.vpm.scheduler;

import com.asqueados.vpm.exceptions.NoEventsOnSchedulerException;
import com.asqueados.vpm.exceptions.UnableToExecuteEventException;
import com.asqueados.vpm.entities.Event;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Scheduler {    
    //members
    long currentTime;
    
    SortedMap<Long, ArrayList<Event>> events;
    
    public Scheduler(){
        currentTime = 0;
        events = new TreeMap<Long, ArrayList<Event>>();
    }
    
    public void schedule(Event event, long time) {
        ArrayList<Event> timedEvents = events.get(time);
        
        if ( timedEvents == null ){
            timedEvents = new ArrayList<Event>();
            events.put(time, timedEvents);
        }
        
        timedEvents.add(event);
    }
    
    public Object executeNext() throws UnableToExecuteEventException, NoEventsOnSchedulerException {
        Object consequence=null;
        Event next=null;
        
        if(events.isEmpty()) {
            throw new NoEventsOnSchedulerException();
        } else {
            // First, try to get the events on the currentTime
            ArrayList<Event> timedEvents = events.get(currentTime);

            // If there aren't events on the currentTime, look for the first
            // events next
            if( (timedEvents == null) || (timedEvents.isEmpty()) ) {
                long nextTime = nextTime();
                
                if ( nextTime > 0 ){
                    currentTime = nextTime;
                    timedEvents = events.get(currentTime);
                }
            }
            
            // If we have obtained the events on the currentTime, or the next
            // get the first event of the list, if exists
            if( (timedEvents != null) && (!timedEvents.isEmpty()) ) {
                next = timedEvents.get(0);
                
                if ( next!=null ){
                    try {
                        Logger.getLogger(Scheduler.class.getName()).log(Level.FINE, "Executing event "+next+"at time "+currentTime+".");
                        consequence = next.execute();
                        Logger.getLogger(Scheduler.class.getName()).log(Level.FINE, "Consequence: "+consequence+".");
                        // If the consequence is an appointed event, schedule it
                        if(consequence instanceof AppointedEvent) {
                            schedule( ((AppointedEvent)consequence).getEvent(), 
                                    ((AppointedEvent)consequence).getTime()+currentTime);
                            
                        }
                        
                    } catch (Exception e) {
                        throw new UnableToExecuteEventException(e);
                    } finally {
                        // Clear executed event
                        timedEvents.remove(0);
                        if (timedEvents.isEmpty()){
                            events.remove(currentTime);
                        }
                    }
                } else throw new NoEventsOnSchedulerException();
            }
            
        }
        
        return consequence;
    }
    
    public boolean hasEvents(){
        return (nextTime()>0);
    }
    
    private long nextTime() {
        long time;
        try {
            time = events.firstKey();
        } catch (NoSuchElementException e) {
            time=-1;
        }
        
        return time;
    }

}
