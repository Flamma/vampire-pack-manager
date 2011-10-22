/*
 * TraitTypes.java
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

package com.asqueados.vpm.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Classifies traits per type
 * 
 * This classification right now only has effect on visualizations.
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class TraitTypes {
    private static boolean configured=false;
    private static List<String> attributes;
    private static List<String> skills;
    
    private static void configure() {
        if(!configured) {
            attributes = new ArrayList<String> ();
            
            attributes.add("physique");
            attributes.add("social");
            attributes.add("mental");
            
            skills = new ArrayList<String> ();
            skills.add("combat"); 
            skills.add("stealth"); 
            skills.add("influence"); 
            skills.add("leadership"); 
            skills.add("finances"); 
            skills.add("awareness"); 
            skills.add("investigation");
            
            configured = true;
        }
    }
    
    /**
     * Get list with name of attribute traits
     * 
     * @return list with name of attribute traits
     */
    public static List<String> getAttributesNames() {
        if(!configured) configure();
        
        return new ArrayList<String> (attributes);
    }

    /**
     * Get list with name of skills traits
     * 
     * @return list with name of skills traits
     */
    public static List<String> getSkillsNames() {
        if(!configured) configure();
        
        return new ArrayList<String> (skills);
    }    
}
