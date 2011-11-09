/*
 * ScheduleHuntTest.java
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

import com.asqueados.vpm.entities.*;
import com.asqueados.vpm.exceptions.*;
import com.asqueados.vpm.tests.SchedulerTest;
import com.asqueados.vpm.xml.PersonageXmlReader;
import com.asqueados.vpm.xml.PersonageXmlWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class ScheduleHuntTest {

    public ScheduleHuntTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Events and tasks schedule
     * 
     * Create scheduler
     * Read character
     * Create TaskHunt
     * Assign character and time to task
     * Schedule task
     * While there are events on scheduler, execute event
     * Save character
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void scheduleHuntTest() throws Exception {
        System.out.println("TEST Hunt");        
        System.out.println("hunt");        
        
        Logger.getLogger(getClass().getName()).setLevel(Level.FINE);
        Scheduler scheduler = new Scheduler();
        
        String path = "data/chars/charSample.xml";

        PersonageXmlReader reader = new PersonageXmlReader(path);

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
        
        assertTrue(scheduler.hasEvents());

        boolean oneEventExecuted = false;
        boolean busyCharacter = false;
        
        while(scheduler.hasEvents() ){
            try {

                scheduler.executeNext();
                oneEventExecuted=true;

            } catch (UnableToExecuteEventException ex) {
                if(ex.getCause() instanceof NotAvailableCharacterException){
                    System.out.println("Character is busy");
                    busyCharacter = true;
                }
                else {
                    Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                    fail("Unable to execute event for unknown reason: "+ex.getCause());
                }
            } catch (NoEventsOnSchedulerException ex) {
                // Nothing, it's normal
            }

        }
        
        assertTrue("At least one event was executed", oneEventExecuted);
        assertTrue("One event could not been executed because character was busy."
                , busyCharacter);
        

        String outPath = "output/chars/out.xml";

        PersonageXmlWriter writer = new PersonageXmlWriter(outPath);

        writer.writeCharacter(character, null);
        writer.fileWrite();
        
    }

}