/*
 * XmlWriter.java
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

import com.asqueados.vpm.entities.Trait;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.FileOutputStream;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class XmlWriter {
    Document doc;
    private String pathName;
    
    public XmlWriter(String pathName) throws XmlWriterException {
        this.pathName = pathName;
        {

            // Test if file can be found
            FileOutputStream out = null;
            try {
                DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
                doc = docBuilder.newDocument();
                out = new FileOutputStream(pathName);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PersonageXmlWriter.class.getName()).log(Level.SEVERE, null, ex);
                throw new XmlWriterException("Unable to create destination file", ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(PersonageXmlWriter.class.getName()).log(Level.SEVERE, null, ex);
                throw new XmlWriterException("Unable to create DOM parser", ex);
            }

            finally {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(PersonageXmlWriter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    public void fileWrite(){
        
        FileOutputStream out = null;
        try {
            OutputFormat format = new OutputFormat(doc);

            out = new FileOutputStream(pathName);

            XMLSerializer serializer = new XMLSerializer(out, format);
            
            serializer.serialize(doc);
            
        } catch (IOException ex) {
            Logger.getLogger(PersonageXmlWriter.class.getName()).log(Level.SEVERE,
                    "File can not be created. This should never happen", ex);

        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(PersonageXmlWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static String translateValue(String type, Object value){
        return value.toString();
    }
    

}
