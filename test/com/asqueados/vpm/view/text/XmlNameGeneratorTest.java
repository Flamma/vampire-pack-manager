/*
 * XmlNameGeneratorTest.java
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

package com.asqueados.vpm.view.text;

import com.asqueados.vpm.app.Application;
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
public class XmlNameGeneratorTest {

    public XmlNameGeneratorTest() {
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
        Application.init();
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of generate method, of class XmlNameGenerator.
     */
    @Test
    public void generate() {
        System.out.println(getIntro());
        System.out.println("generate");
        
        String result=null;
        XmlNameGenerator instance = new XmlNameGenerator(Application.getDataConfiguration().getOption("nameGenerator"));

        String sex = "male";        
        result = instance.generate(sex);
        assertNotNull(result);
        assertTrue("Not empty", result.length() > 0);
        System.out.println(sex+": "+result);
        
        sex = "female";        
        result = instance.generate(sex);
        assertNotNull(result);
        assertTrue("Not empty", result.length() > 0);
        System.out.println(sex+": "+result);

    }

}