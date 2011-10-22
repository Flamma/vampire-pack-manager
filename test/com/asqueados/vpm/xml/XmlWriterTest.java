/*
 * XmlWriterTest.java
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

import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Element;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class XmlWriterTest {
    private final String basePath = "output/test/";
    
    public XmlWriterTest() {
    }
    
    private String getIntro() {
        return "TEST XmlWriter";
    }

    private List<Trait> getTraits() {
        List<Trait> traits = new ArrayList<Trait>();
        
        for (int i=0; i<10; i++) {
            String name = "name"+i;
            String type;
            Object value;
            
            switch (i%3) {
                case 0:
                    type="string";
                    value="value"+i;
                    break;
                case 1:
                    type="integer";
                    value=new Integer(i);
                    break;
                case 2:
                default:
                    type="boolean";
                    value=( (i%2) == 0 );
                    break;
            }
            
            Trait trait = new Trait(name, type, value);
            traits.add(trait);
        }
        
        return traits;
        
    }
    private Personage getCharacter() {
        List<Trait> traits = getTraits();
        Personage character = new Personage(traits);  
        
        return character;
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
     * Test of writeTrait method, of class XmlWriter.
     */
    @Test
    public void writeTrait() throws XmlWriterException {
        System.out.println(getIntro());
        System.out.println("writeTrait");
        
        List<Trait> traits = getTraits();

        XmlWriter instance = new XmlWriter(basePath+"traits.xml");
        
        for(Trait trait: traits) {
            instance.writeTrait(trait, null);
        }

    }

    /**
     * Test of writeCharacter method, of class XmlWriter.
     */
    @Test
    public void writeCharacter() throws XmlWriterException {
        System.out.println(getIntro());
        System.out.println("writeCharacter");
        
        Personage character = getCharacter();
        Element father = null;
        XmlWriter instance = new XmlWriter(basePath+"character.xml");
        instance.writeCharacter(character, father);
    }


    /**
     * Test of fileWrite method, of class XmlWriter.
     */
    @Test
    public void fileWrite() {

        System.out.println(getIntro());
        System.out.println("fileWrite");

        Personage character = getCharacter();
        Element father = null;
        XmlWriter instance;
        
        try {
            instance = new XmlWriter(basePath + "character.xml");
            instance.writeCharacter(character, father);
            instance.fileWrite();


        } catch (XmlWriterException ex) {
            Logger.getLogger(XmlWriterTest.class.getName()).log(Level.SEVERE, null, ex);
            fail(ex.getMessage());
        }
        List<Trait> traits = getTraits();

        for (Trait trait : traits) {
            try {
                instance = new XmlWriter(basePath + "trait_" + trait.getName() + ".xml");
                System.out.println("Writing trait " + trait + " to file " + basePath + "trait_" + trait.getName() + ".xml");
                instance.writeTrait(trait, null);
            } catch (XmlWriterException ex) {
                Logger.getLogger(XmlWriterTest.class.getName()).log(Level.SEVERE, null, ex);
                fail(ex.getMessage());
            }

        }

        
    }

}