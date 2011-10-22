/*
 * XmlNameGenerator.java
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

import com.asqueados.vpm.xml.NameXmlReader;
import com.asqueados.vpm.xml.XmlReaderException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class XmlNameGenerator implements NameGenerator {
    private String fileName;
    private int nNames = 1;
    private int nLastNames = 1;
    
    public XmlNameGenerator(String fileName) {
        this.fileName = fileName;
    }

    public XmlNameGenerator(String fileName, int nNames, int nLastNames) {
        this.fileName = fileName;
        this.nNames = nNames;
        this.nLastNames = nLastNames;
    }
    
    public String generate(String sex) {
        StringBuffer name = new StringBuffer();
        Random roller = new Random();

        try {
            NameXmlReader reader = new NameXmlReader(fileName);
            List<String> names = reader.getNames(sex);

            for (int i = 0; i < nNames; i++) {
                int elected = roller.nextInt(names.size());
                name.append(names.get(elected)+" ");
            }
            
            names = reader.getLastNames();

            for (int i = 0; i < nLastNames; i++) {
                int elected = roller.nextInt(names.size());
                name.append(names.get(elected)+" ");
            }
            
            name.deleteCharAt(name.length()-1);

        } catch (XmlReaderException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Could not read names xml.", ex);
        }
        
        return new String(name);        
    }

}
