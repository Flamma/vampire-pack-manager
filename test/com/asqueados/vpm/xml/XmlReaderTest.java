/*
 * XmlReaderTest.java
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

package com.asqueados.vpm.xml;

import com.asqueados.vpm.entities.Archetype;
import com.asqueados.vpm.entities.Personage;
import java.util.Map;
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
public class XmlReaderTest {

    private static final String characterPath="data/chars/sample.xml";
    private static final String archetypesPath="data/templates/archetypes.xml";
    public XmlReaderTest() {
    }
    
    private String getIntro() {
        return "TEST XmlReader";
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
     * Test of readCharacter method, of class XmlReader.
     */
    @Test
    public void readCharacter() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("readCharacter");
        
        XmlReader instance = new XmlReader(characterPath);
        Personage result = instance.readCharacter();
        assertNotNull(result);
        System.out.println(result);

    }


    /**
     * Test of readArchetypes method, of class XmlReader.
     */
    @Test
    public void readArchetypes() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("readArchetypes");
        
        XmlReader instance = new XmlReader(archetypesPath);
        Map<String, Archetype> result = instance.readArchetypes();
        assertNotNull(result);
        assertTrue("Number of archetypes greater than 0", result.size()>0);
        
        for( String key: result.keySet()) {
            System.out.println("Archetype "+key+": "+result.get(key));
        }
    }

}