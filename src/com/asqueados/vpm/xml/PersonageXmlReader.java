/*
 * PersonageXmlReader.java
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

package com.asqueados.vpm.xml;

import com.asqueados.vpm.entities.Archetype;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;

import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contains the methods to read traits, characters ande archetypes 
 * from XML files.
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PersonageXmlReader extends XmlReader {
    public PersonageXmlReader(String pathName) throws XmlReaderException {
        super(pathName);
    }
    
    public Personage readCharacter (){
        NodeList charNodes = doc.getElementsByTagName("character");
        
        if(charNodes.getLength() > 0)
            return readCharacter((Element) charNodes.item(0));
        
        return null;
    }
    
    public Personage readCharacter (Element charElement){
        NodeList traitNodes = charElement.getElementsByTagName("trait");
        
        List<Trait> traits = new ArrayList<Trait>();
        for(int i=0; i<traitNodes.getLength(); i++){
            Element traitElement = (Element) traitNodes.item(i);
            
            Trait trait = readTrait(traitElement);
            
            if( trait != null)
                traits.add(trait);
        }
        
        String id = charElement.getAttribute("id");
        if(id.equals(""))
            id = null;
        
        Personage character = new Personage(id, traits);
        
        return character;
    }

    public Trait readTrait(Element traitElement) {
        Trait trait = null;
        
        String name = traitElement.getAttribute("name");
        String type = traitElement.getAttribute("type");
        Object value = createValue(type, traitElement.getAttribute("value"));
        
        if(value == null) {
            Logger.getLogger(PersonageXmlReader.class.getName()).log(Level.WARNING, 
                        "Could not read attribute value of attribute " + name);
        } else {
            trait = new Trait(name, type, value);
        }
        
        return trait;
    }
    
    /**
     * Create an object of the specified type. 
     * 
     * @param type
     * @param textValue the value in text format
     * @return the value or null if the value cannot be built
     */
    private static Object createValue(String type, String textValue){
        Object value = null;
        
        if (type.equals(Trait.STRING) ){
            value = textValue;
        } else if (type.equals(Trait.INTEGER)) {
            try {
                value = Integer.parseInt(textValue);
            } catch (NumberFormatException ex) {
                Logger.getLogger(PersonageXmlReader.class.getName()).log(Level.WARNING, 
                        "Wrong integer", ex);
            }
        } else if (type.equals(Trait.BOOLEAN)) {
            value = textValue.equals("true");
        }
        
        return value;
    }

    public Archetype readArchetype (Element charElement){
        NodeList traitNodes = charElement.getElementsByTagName("trait");
        
        List<Trait> traits = new ArrayList<Trait>();
        for(int i=0; i<traitNodes.getLength(); i++){
            Element traitElement = (Element) traitNodes.item(i);
            
            Trait trait = readTrait(traitElement);
            
            if( trait != null)
                traits.add(trait);
        }
        
        String id = charElement.getAttribute("id");
        
        Archetype archetype = new Archetype(id, traits);
        
        return archetype;
    }
    
    public Map<String, Archetype> readArchetypes (){
        NodeList charNodes = doc.getElementsByTagName("archetypes");
        
        if(charNodes.getLength() > 0)
            return readArchetypes((Element) charNodes.item(0));
        
        return null;
    }
    
    public Map<String, Archetype> readArchetypes (Element charElement){
        NodeList archetypeNodes = charElement.getElementsByTagName("archetype");
        
        Map<String, Archetype> archetypes = new HashMap<String, Archetype>();
        for(int i=0; i<archetypeNodes.getLength(); i++){
            Element archetypeElement = (Element) archetypeNodes.item(i);
            
            Archetype archetype = readArchetype(archetypeElement);
            
            if( archetype != null)
                archetypes.put(archetype.getId(), archetype);
        }
                
        return archetypes;
    }
    
}
