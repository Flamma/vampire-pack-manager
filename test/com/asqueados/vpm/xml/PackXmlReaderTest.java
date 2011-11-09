/*
 * PackXmlReaderTest.java
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

import com.asqueados.vpm.entities.Pack;
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
public class PackXmlReaderTest {
    private static final String packPath="data/chars/packSample.xml";
    
    public PackXmlReaderTest() {
    }
    
    private String getIntro() {
        return "TEST "+getClass().getName();
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
     * Test of readPack method, of class PackXmlReader.
     */
    @Test
    public void readPack() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("readPack");

        PackXmlReader instance = new PackXmlReader(packPath);
        Pack result = instance.readPack();
        assertNotNull(result);
        System.out.println("READED PACK: "+result);
        assertTrue("Number of characters greater than 0", result.getCharacters().size()>0);
        assertTrue("Number of traits greater than 0", result.getTraits().size()>0);
        
    }

}