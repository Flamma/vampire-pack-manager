/*
 * PersonageXmlWriter.java
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

import com.asqueados.vpm.entities.Trait;
import org.w3c.dom.Element;

import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;
/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PersonageXmlWriter extends XmlWriter {
    public PersonageXmlWriter(String pathName) throws XmlWriterException {
        super(pathName);
    }
    
    public void writeCharacter(Personage character, Element father){
        if(father == null){
            father = doc.getDocumentElement();
        }
            
        
        Element charElement = doc.createElement("character");
        
        charElement.setAttribute("id", character.getId() );
        
        for( Trait trait: character.getTraits() ){
            writeTrait(trait, charElement);
        }
        

        if (father == null)
            doc.appendChild(charElement);
        else 
            father.appendChild(charElement);
        
    }
    
    public void writeTrait(Trait trait, Element father) {
        if(father == null){
            father = doc.getDocumentElement();
        }
        
        Element traitElement = doc.createElement("trait");
        
        traitElement.setAttribute("name", trait.getName());
        traitElement.setAttribute("type", trait.getType());
        traitElement.setAttribute("value", translateValue(trait.getType(), 
                trait.getValue()));
        
        if (trait.getCost() != null){
            traitElement.setAttribute("cost", trait.getCost().toString());
        }

        if (father == null)
            doc.appendChild(traitElement);
        else         
            father.appendChild(traitElement);
        
    }
}
