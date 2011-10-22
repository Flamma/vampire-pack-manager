/*
 * XmlWriteTest.java
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

import com.asqueados.vpm.entities.Trait;
import com.asqueados.vpm.xml.PersonageXmlWriter;
import com.asqueados.vpm.xml.XmlWriterException;
import java.util.ArrayList;
import java.util.List;

import com.asqueados.vpm.entities.Personage;
/**
 * @deprecated
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
@Deprecated
public class XmlWriteTest {
    public static void main(String args[]) throws XmlWriterException {
        String path = "output/chars/sample.xml";
        
        PersonageXmlWriter writer = new PersonageXmlWriter(path);
        
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
        
        Personage character = new Personage(traits);
        
        writer.writeCharacter(character, null);
        writer.fileWrite();
        
    }

}
