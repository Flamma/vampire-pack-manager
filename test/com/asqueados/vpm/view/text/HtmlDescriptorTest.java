/*
 * PlainDescriptorTest.java
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
import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;
import com.asqueados.vpm.xml.PersonageXmlReader;
import com.asqueados.vpm.xml.XmlReaderException;
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
public class HtmlDescriptorTest {

    public HtmlDescriptorTest() {
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
    
    private Descriptor getDescriptor() {
        return new HtmlDescriptor();
    }
    
    private String getIntro() {
        return "TEST HtmlDescriptor";
    }
    
    private Trait getTrait() {
        return new Trait("trait", "integer", 5);
    }
    
    private Personage getPersonage() throws XmlReaderException {
        String path = "data/chars/sample.xml";
        PersonageXmlReader reader = new PersonageXmlReader(path);
        Personage character = reader.readCharacter();
        
        return character;

    }

    /**
     * Test of getTraitValueDescription method, of class PlainDescriptor.
     */
    @Test
    public void getTraitValueDescription() {
        System.out.println(getIntro());
        System.out.println("getTraitValueDescription");
        
        Trait trait = getTrait(); 
        Descriptor instance = getDescriptor();

        String result = instance.getTraitValueDescription(trait);
        assertNotNull(result);
        System.out.println(result);
    }

    /**
     * Test of getRaceDescription method, of class PlainDescriptor.
     */
    @Test
    public void getRaceDescription() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("getRaceDescription");
        
        Personage personage = getPersonage();
        Descriptor instance = getDescriptor();

        String result = instance.getRaceDescription(personage);
        assertNotNull(result);
        
        System.out.println(result);
    }

    /**
     * Test of getPersonageAttributesDescription method, of class PlainDescriptor.
     */
    @Test
    public void getPersonageAttributesDescription() throws XmlReaderException {
        System.out.println(getIntro());        
        System.out.println("getPersonageAttributesDescription");
        
        Personage character = getPersonage();
        Descriptor instance = getDescriptor();

        String result = instance.getPersonageAttributesDescription(character);
        assertNotNull(result);
        
        System.out.println(result);
    }

    /**
     * Test of getPersonageDescription method, of class PlainDescriptor.
     */
    @Test
    public void getPersonageDescription() throws XmlReaderException {
        System.out.println(getIntro());
        System.out.println("getPersonageDescription");
        
        Personage personage = getPersonage();
        Descriptor instance = getDescriptor();

        String result = instance.getPersonageDescription(personage);
        assertNotNull(result);
        
        System.out.println(result);
    }
}