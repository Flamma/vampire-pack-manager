/*
 * XmlReader.java
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

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.w3c.dom.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for reading from XML files.
 * 
 * Concrete classes inherit from it
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public abstract class XmlReader {
    Document doc;
    
    public XmlReader(String pathName) throws XmlReaderException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);

            DocumentBuilder parser = factory.newDocumentBuilder();

            doc = parser.parse(pathName);
            
        } catch (SAXException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            throw new XmlReaderException("Unable to create DOM parser", ex);
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            throw new XmlReaderException("Unable to read XML file", ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            throw new XmlReaderException("Unable to read XML file", ex);
        }
        
    }

}
