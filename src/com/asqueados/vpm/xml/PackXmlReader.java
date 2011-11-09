/*
 * PackXmlReader.java
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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PackXmlReader extends PersonageXmlReader {
    public PackXmlReader(String pathName) throws XmlReaderException {
        super(pathName);
    }
    
    public Pack readPack() {
        NodeList charNodes = doc.getElementsByTagName("pack");
        
        if(charNodes.getLength() > 0)
            return readPack((Element) charNodes.item(0));
        
        return null;
    }

    private Pack readPack(Element element) {
        String id = element.getAttribute("id");
        if(id.equals(""))
            id = null;
        
        String blockName=null;
        Element charsElement=null;
        Element traitsElement=null;
        try {
            blockName = "characters";
            charsElement = (Element) element.getElementsByTagName(blockName).item(0);
            blockName = "traits";
            traitsElement = (Element) element.getElementsByTagName(blockName).item(0);
        } catch (NullPointerException ex) {
            Logger.getLogger(getClass().getName()).log(Level.WARNING, 
                        blockName + " block not found");
            return null;
        }
                
        // Read characters
        List<Personage> characters = new ArrayList<Personage>();
        NodeList charNodes = charsElement.getElementsByTagName("character");
        
        for(int i=0; i<charNodes.getLength(); i++) {
            Element charElement = (Element) charNodes.item(i);
            Personage character = readCharacter(charElement);
            if(character!=null)
                characters.add(character);
        }
        
        // Read characters
        List<Trait> traits = new ArrayList<Trait>();
        NodeList traitsNodes = traitsElement.getElementsByTagName("trait");
        
        for(int i=0; i<traitsNodes.getLength(); i++) {
            Element traitElement = (Element) traitsNodes.item(i);
            Trait trait = readTrait(traitElement);
            if(trait!=null)
                traits.add(trait);
        }
        
        Pack pack = new Pack(id);
        pack.setCharacters(characters);
        pack.setTraits(traits);
        
        
        return pack;
    }
    
    

}
