/*
 * NameXmlReader.java
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

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.*;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class NameXmlReader extends XmlReader{
    public NameXmlReader(String pathName) throws XmlReaderException {
        super(pathName);
    }
    public List<String> getNames() {
        return getNames(null);
    }
    public List<String> getNames(String sex) {
        List<String> names = new ArrayList<String>();
        NodeList nodes = doc.getElementsByTagName("name");
        
        for(int i=0; i<nodes.getLength(); i++){
            Element element = (Element) nodes.item(i);
            
            if( sex==null || element.getAttribute("sex")==null || 
                    element.getAttribute("sex").equals(sex) ) {
                
                String name = element.getTextContent();

                if( name != null && name.length() > 0 )
                    names.add(name);
            }
        }

        
        return names;
    }

    public List<String> getLastNames() {
        List<String> names = new ArrayList<String>();
        NodeList nodes = doc.getElementsByTagName("lastname");
        
        for(int i=0; i<nodes.getLength(); i++){
            Element element = (Element) nodes.item(i);
            String name = element.getTextContent();
            
            if( name != null && name.length() > 0 )
                names.add(name);
        }

        
        return names;
    }    
}
