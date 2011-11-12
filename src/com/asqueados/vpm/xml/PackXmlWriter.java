/*
 * PackXmlWriter.java
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
import org.w3c.dom.*;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PackXmlWriter extends PersonageXmlWriter {
    public PackXmlWriter(String pathName) throws XmlWriterException {
        super(pathName);
    }
    
    public void writePack(Pack pack, Element father) {
        if(father == null){
            father = doc.getDocumentElement();
        }
            
        Element element = doc.createElement("pack");
        
        element.setAttribute("id", pack.getId() );
        
        // Write traits
        Element traitsElement = doc.createElement("traits");
        for( Trait trait: pack.getTraits() ){
            writeTrait(trait, traitsElement);
        }
        element.appendChild(traitsElement);
        
        // Write characters
        Element charsElement = doc.createElement("characters");
        for( Personage character: pack.getCharacters()) {
            writeCharacter(character, charsElement);
        }
        element.appendChild(charsElement);
        

        if (father == null)
            doc.appendChild(element);
        else 
            father.appendChild(element);
        
    }
    
    public void writePack(Pack pack) {
        writePack(pack, null);
    }

}
