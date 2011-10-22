/*
 * ArchetypeFactory.java
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

package com.asqueados.vpm.entities;

import com.asqueados.vpm.app.Application;
import com.asqueados.vpm.exceptions.UnableToCreateArchetypeException;
import com.asqueados.vpm.xml.PersonageXmlReader;
import com.asqueados.vpm.xml.XmlReaderException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class ArchetypeFactory {
    private static String archetypesFile;

    private static void loadProperties() {
        archetypesFile = Application.dataConfiguration.getOption("archetypesFile");
    }
    
    private static String getArchetypesFile() {
        if (archetypesFile == null)
            loadProperties();
        
        return archetypesFile;
    }
    
    public static Map<String, Archetype> getArchetypesMap() throws UnableToCreateArchetypeException {
        Map<String, Archetype> archetypes=null;
        try {
            PersonageXmlReader reader = new PersonageXmlReader(getArchetypesFile());

            archetypes = reader.readArchetypes();
        } catch (XmlReaderException ex) {
            Logger.getLogger(ArchetypeFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new UnableToCreateArchetypeException(ex);
        }
        
        return archetypes;
    }

    public static Collection<Archetype> getArchetypes() throws UnableToCreateArchetypeException {
        Collection <Archetype> archetypes=null;
        Map<String, Archetype> archetypesMap=getArchetypesMap();
        
        archetypes = archetypesMap.values();
        
        return archetypes;
    }
}
