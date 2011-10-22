/*
 * HuntTest.java
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

package com.asqueados.vpm.entities;

import com.asqueados.vpm.xml.XmlReader;
import com.asqueados.vpm.xml.XmlWriter;
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
public class HuntTest {

    public HuntTest() {
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
     * A test for tasks normal behaviour
     * 
     * Open data/chars/sample.xml character
     * Assign character to task
     * Do task
     * Save modified character to output/chars/out.xml
     * 
     * @throws java.lang.Exception
     */
    @Test
    public void hunt() throws Exception {
        System.out.println("TEST Hunt");        
        System.out.println("hunt");        
        
        String path = "data/chars/sample.xml";

        XmlReader reader = new XmlReader(path);
        assertNotNull(reader);

        Personage character = reader.readCharacter();
        assertNotNull(character);

        Task task = new TaskHunt();
        assertNotNull(task);
        task.assign(character);
        task.setTime(10);

        task.doTask();

        String outPath = "output/chars/out.xml";

        XmlWriter writer = new XmlWriter(outPath);
        assertNotNull(writer);

        writer.writeCharacter(character, null);
        writer.fileWrite();
        
    }

}