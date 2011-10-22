/*
 * Task.java
 * 
 * Copyright (c) 2010 Pablo J. Urbano Santos <flamma at member.fsf.org>. 
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

package com.asqueados.vpm.entities;

import com.asqueados.vpm.exceptions.NotAvailableCharacterException;
import com.asqueados.vpm.scheduler.AppointedEvent;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public abstract class Task implements Event {
    //static constants
    public final String TRAITBUSY = "busy";
    
    //members
    protected Set<Personage> characters;
    protected int time;
    protected boolean begun;
    
    public Task() {
        characters = new HashSet<Personage>();
        time = 0;
        begun=false;
    }
    
    public void assign(Personage character) {
        characters.add(character);
    }
    
    public void setTime(int time){
        this.time = time;
    }
    
    public boolean hasBegun(){
        return begun;
    }
    
    public int begin() throws NotAvailableCharacterException {
        // Check if all characters are available
        for(Personage character : characters){
            if((Boolean) character.getValue(TRAITBUSY)){
                throw new NotAvailableCharacterException(this, character);
            }
        }

        // If all are availabe, make them busy
        for(Personage character : characters){
            character.setValue(TRAITBUSY, true);
        }
        
        begun=true;
        
        return time;
    }
    
    public Object execute() throws NotAvailableCharacterException {
        Object result=null;
        
        if(!hasBegun()){
            int nextTime=begin();
            result = new AppointedEvent(this, nextTime);
        } else {
            result=doTask();
            // Characters are done. Free them.
            for(Personage character : characters){
                character.setValue(TRAITBUSY, false);
            }

        }
        
        return result;
    }
    
    public abstract Object doTask();
}
