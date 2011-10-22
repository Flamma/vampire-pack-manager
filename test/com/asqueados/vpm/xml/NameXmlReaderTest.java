/*
 * NameXmlReaderTest.java
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

import java.util.List;
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
public class NameXmlReaderTest {

    public NameXmlReaderTest() {
    }
    
    private String getIntro() {
        return "TEST "+getClass().getName();
    }
    
    private final String fileNames="data/templates/names_es.xml";

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
     * Test of getNames method, of class NameXmlReader.
     */
    @Test
    public void getNames() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("getNames");
        
        NameXmlReader instance = new NameXmlReader(fileNames);
        List<String> result = instance.getNames();
        assertNotNull(result);
        assertTrue("Resultado no vacío", result.size() > 0 );
        
        for(String name : result) {
            assertNotNull(name);
        }
        System.out.println(result);
    }

    /**
     * Test of getLastNames method, of class NameXmlReader.
     */
    @Test
    public void getLastNames() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("getLastNames");
        
        NameXmlReader instance = new NameXmlReader(fileNames);
        List<String> result = instance.getLastNames();
        assertNotNull(result);
        assertTrue("Resultado no vacío", result.size() > 0 );
        
        for(String name : result) {
            assertNotNull(name);
        }
        System.out.println(result);
        
    }

}