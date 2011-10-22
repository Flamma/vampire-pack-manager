/*
 * SchedulerTest.java
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

package com.asqueados.vpm.tests;

import com.asqueados.vpm.exceptions.NoEventsOnSchedulerException;
import com.asqueados.vpm.exceptions.UnableToExecuteEventException;
import com.asqueados.vpm.exceptions.NotAvailableCharacterException;
import com.asqueados.vpm.scheduler.Scheduler;
import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Task;
import com.asqueados.vpm.entities.TaskHunt;
import com.asqueados.vpm.xml.XmlReader;
import com.asqueados.vpm.xml.XmlReaderException;
import com.asqueados.vpm.xml.XmlWriter;
import com.asqueados.vpm.xml.XmlWriterException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @deprecated
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
@Deprecated
public class SchedulerTest {
    public static void main(String args[]) throws XmlReaderException, XmlWriterException{
        Logger.getLogger(Scheduler.class.getName()).setLevel(Level.FINE);
        Scheduler scheduler = new Scheduler();
        
        String path = "data/chars/sample.xml";

        XmlReader reader = new XmlReader(path);

        Personage character = reader.readCharacter();
        
        // We add three tasks
        // The second should not be executed, as character should be busy
        Task task = new TaskHunt();
        task.assign(character);
        task.setTime(10);

        scheduler.schedule(task, 15);

        task = new TaskHunt();
        task.assign(character);
        task.setTime(10);
        
        scheduler.schedule(task, 20);
        
        task = new TaskHunt();
        task.assign(character);
        task.setTime(10);
        
        scheduler.schedule(task, 50);

        
        while(scheduler.hasEvents() ){
            try {

                scheduler.executeNext();

            } catch (UnableToExecuteEventException ex) {
                if(ex.getCause() instanceof NotAvailableCharacterException){
                    System.out.println("Character is busy");
                }
                else Logger.getLogger(SchedulerTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoEventsOnSchedulerException ex) {
                // Nothing, it's normal
            }

        }

        String outPath = "output/chars/out.xml";

        XmlWriter writer = new XmlWriter(outPath);

        writer.writeCharacter(character, null);
        writer.fileWrite();
        
    }

}
