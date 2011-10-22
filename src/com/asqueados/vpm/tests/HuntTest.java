/*
 * HuntTest.java
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

package com.asqueados.vpm.tests;

import com.asqueados.vpm.xml.XmlReader;
import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Task;
import com.asqueados.vpm.entities.TaskHunt;
import com.asqueados.vpm.xml.XmlReaderException;
import com.asqueados.vpm.xml.XmlWriter;
import com.asqueados.vpm.xml.XmlWriterException;

/**
 * Test of executing a task
 * 
 * Loads a character, executes the task TaskHunt, and stores it, modified by the task.
 * 
 * @deprecated
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
@Deprecated
public class HuntTest {
    public static void main(String args[]) throws XmlReaderException, XmlWriterException {
        String path = "data/chars/sample.xml";

        XmlReader reader = new XmlReader(path);

        Personage character = reader.readCharacter();

        Task task = new TaskHunt();
        task.assign(character);
        task.setTime(10);

        task.doTask();

        String outPath = "output/chars/out.xml";

        XmlWriter writer = new XmlWriter(outPath);

        writer.writeCharacter(character, null);
        writer.fileWrite();

        
    }

}
