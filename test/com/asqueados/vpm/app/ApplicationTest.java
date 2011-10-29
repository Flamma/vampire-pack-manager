/*
 * ApplicationTest.java
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

package com.asqueados.vpm.app;

import com.asqueados.vpm.configuration.Configuration;
import com.asqueados.vpm.view.color.ColorDescriptor;
import com.asqueados.vpm.view.i18n.Translator;
import com.asqueados.vpm.view.text.Descriptor;
import com.asqueados.vpm.view.text.NameGenerator;
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
public class ApplicationTest {

    public ApplicationTest() {
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
     * Test of init method, of class Application.
     */
    @Test
    public void init() {
        System.out.println(getIntro());
        System.out.println("init");
        Application.init();
        
        assertNotNull(Application.getConfiguration());
        assertNotNull(Application.getDataConfiguration());
        assertNotNull(Application.getTranslator());
        assertNotNull(Application.getTextDescriptor());
        assertNotNull(Application.getColor());
        assertNotNull(Application.getNameGenerator());
        // Add any new getter Application gets here
        
    }

    /**
     * Test of getColor method, of class Application.
     */
    @Test
    public void getColor() {
        System.out.println(getIntro());
        System.out.println("getColor");
        ColorDescriptor result = Application.getColor();
        assertNotNull(result);
    }

    /**
     * Test of getConfiguration method, of class Application.
     */
    @Test
    public void getConfiguration() {
        System.out.println(getIntro());
        System.out.println("getConfiguration");

        Configuration result = Application.getConfiguration();
        assertNotNull(result);
    }

    /**
     * Test of getDataConfiguration method, of class Application.
     */
    @Test
    public void getDataConfiguration() {
        System.out.println(getIntro());
        System.out.println("getDataConfiguration");
        
        Configuration result = Application.getDataConfiguration();
        assertNotNull(result);
    }

    /**
     * Test of getNameGenerator method, of class Application.
     */
    @Test
    public void getNameGenerator() {
        System.out.println(getIntro());
        System.out.println("getNameGenerator");

        NameGenerator result = Application.getNameGenerator();
        assertNotNull(result);
    }

    /**
     * Test of getTextDescriptor method, of class Application.
     */
    @Test
    public void getTextDescriptor() {
        System.out.println(getIntro());
        System.out.println("getTextDescriptor");

        Descriptor result = Application.getTextDescriptor();
        assertNotNull(result);
    }

    /**
     * Test of getTranslator method, of class Application.
     */
    @Test
    public void getTranslator() {
        System.out.println(getIntro());
        System.out.println("getTranslator");

        Translator result = Application.getTranslator();
        assertNotNull(result);
    }

}