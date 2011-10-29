/*
 * Descriptor.java
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

import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;

/**
 * Provide text descriptions
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public interface Descriptor {
    /**
     * Get a text description of the value of the trait. Some traits are treated individually, by name, while the rest have generic descriptions
     * 
     * @param trait
     * @return a text description of the value of the trait.
     */
    public String getTraitValueDescription(Trait trait);
    /**
     * Returns a description of the character type (e.g: vampire, human)
     * 
     * Race can be obtained from several taits
     * 
     * @param personage
     * @return a description of the character type (e.g: vampire, human)
     */
    public String getRaceDescription(Personage character);
    /**
     * Returns a description of the attributes of a character
     * 
     * @param personage
     * @return a description of the attributes of a character
     */    
    public String getPersonageAttributesDescription(Personage character);
    /**
     * Returns an introduction to the character
     * 
     * @param personage
     * @return an introduction to the character
     */    
    public String getPersonageIntroDescription(Personage character);
    /**
     * Returns a description of the skills of a character
     * 
     * @param personage
     * @return a description of the skills of a character
     */    
    public String getPersonageSkillsDescription(Personage character);
    /**
     * Returns full description of a character
     * 
     * @param personage
     * @return full description of a character
     */
    public String getPersonageDescription(Personage personage);
    
}
