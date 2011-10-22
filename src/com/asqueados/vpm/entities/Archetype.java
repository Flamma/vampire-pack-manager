/*
 * Archetype.java
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An archetype is what a character is/was on his life. It could be a profession.
 * 
 * When a character is generated, the archetype give bonuses to some traits.
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Archetype {
    /**
     * @var id
     */    
    private String id;
    /**
     * @var traits
     * The value of this traits will be treated as bonuses
     */
    private Map<String, Trait> traits; 
    
    public Archetype(String name) {
        this.id = name;
        this.traits = new HashMap<String, Trait>();
    }
    
    public Archetype(String name, List<Trait> traits) {
        this.id = name;
        this.traits = new HashMap<String, Trait>();
        
        for(Trait trait: traits){
            if(trait == null){
                Logger.getLogger(Archetype.class.getName()).log(Level.SEVERE, 
                        "Trait is null. This should never happen");
            } else {
                this.traits.put(trait.getName(), trait);
            }
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Trait> getTraits() {
        List<Trait> result = new ArrayList<Trait>();
        
        for( String name: traits.keySet() ){
            result.add(traits.get(name));
        }
        
        return result;
        
    }

    public void setTraits(Map<String, Trait> traits) {
        this.traits = traits;
    }
        
        
    /**
     * Get the archetype's trait that has its name
     * 
     * @param traitName
     * @return trait with name traitName or null if the archetype doesn't have it
     */
    public Trait getTrait(String traitName){
        return traits.get(traitName);
    }
    
    /**
     * If the archetype has a trait with name traitName, get the value of this 
     * trait
     * 
     * @param traitName
     * @return value of the trait with name traitName or false if it does not 
     * exist
     */
    public Object getValue(String traitName){
        Object result = null;
        
        Trait trait = traits.get(traitName);
        
        if(trait != null)
            result = trait.getValue();
        else
            result = false;
            
        return result;
    }
    
    /**
     * If the archetype has a trait with name traitName, it sets its value to
     * value. Otherwise, it tries to create a new Trait with that value, guessing
     * the type. If the type can't be guessed, the character is unmodified.
     * 
     * @param traitName
     * @param value
     */
    public void setValue(String traitName, Object value){
        Trait trait = traits.get(traitName);
        
        if(trait != null) {
            trait.setValue(value);
        } else {
            String type = Trait.getTypeFromValue(value);
            
            if (type != null){
                trait = new Trait(traitName, type, value);
            }
            
            traits.put(traitName, trait);
        }
    }
    
    // Object Overrides
    @Override
    public String toString(){
        StringBuffer text = new StringBuffer("Archetype "+id+":");
        
        for (String name: traits.keySet()){
            text.append("\n\t"+traits.get(name));
        }
        
        text.append("\n--\n");
        
        return new String(text);
    }
        
    


}
