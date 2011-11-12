/*
 * PackXmlWriterTest.java
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
import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;
import java.util.ArrayList;
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
public class PackXmlWriterTest {
    private final String basePath = "output/test/";

    public PackXmlWriterTest() {
    }
    
    private String getIntro() {
        return "TEST " + PersonageXmlWriter.class.getName();
    }

    private List<Trait> getTraits(int start) {
        List<Trait> traits = new ArrayList<Trait>();
        
        for (int i=start; i<start+10; i++) {
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
    
    private Personage getCharacter(int start) {
        List<Trait> traits = getTraits(start);
        Personage character = new Personage(traits);  
        
        return character;
    }
    
    private Pack getPack() {
        Pack pack = new Pack();
        List<Trait> traits = getTraits(0);
        pack.setTraits(traits);
        
        for(int i=10; i<60; i+=10) {
            Personage character = getCharacter(i);
            pack.addCharacter(character);
        }
        
        return pack;
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
     * Test of writePack method, of class PackXmlWriter.
     */
    @Test
    public void writePack() throws XmlWriterException {
        System.out.println("writePack");
        System.out.println(getIntro());
        
        Pack pack = getPack();
        System.out.println(pack);
        PackXmlWriter instance = new PackXmlWriter(basePath+"pack.xml");
        instance.writePack(pack);        
        instance.fileWrite();
    }

}