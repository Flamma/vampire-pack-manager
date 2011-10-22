/*
 * PropertiesConfiguration.java
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

package com.asqueados.vpm.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PropertiesConfiguration implements Configuration {
    private Map<String, String> cache = new HashMap<String, String>();
    private String propFile;

    public PropertiesConfiguration(String propFile) {
        this.propFile = propFile;
    }

    public String getOption(String name) {
        String value = cache.get(name);
        
        if(value==null) {
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream(propFile));
                value = properties.getProperty(name);
            } catch (IOException ex) {
                Logger.getLogger(PropertiesConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return value;
    }

}
