/*
 * Personage.java
 * 
 * Copyright (c) 2010 Pablo J. Urbano Santos <flamma at member.fsf.org>. 
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class that encapsulates a character
 * A character has traits. You can set and query for them
 * 
 * Notice: I wanted to name this class "Character" but this is a default java class
 * which brings a lot of troubles. Class is renamed as "Personage" but data files will
 * still use "character".
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Personage {
    /**
     * @var traits 
     */
    private Map<String, Trait> traits;
    /**
     * @var id UUID of this character. If not specified, it is auto-generated
     */
    private String id;

    public Personage(String id) {
        traits = new HashMap<String, Trait>();
        
        if(id==null)
            this.id = UUID.randomUUID().toString();
        else
            this.id = id;

    }

    public Personage(String id, Map<String, Trait> traits) {
        this.traits = traits;
        
        if(id==null)
            this.id = UUID.randomUUID().toString();
        else
            this.id = id;
    }
    
    public Personage(String id, List<Trait> traits) {
        this(id);
        
        for(Trait trait: traits){
            if(trait == null){
                Logger.getLogger(Personage.class.getName()).log(Level.SEVERE, 
                        "Trait is null. This should never happen");
            } else {
                this.traits.put(trait.getName(), trait);
            }
        }

        if(id==null)
            this.id = UUID.randomUUID().toString();
        else
            this.id = id;
    }
    
    public Personage(){
        this(null, new ArrayList<Trait>());
    }
    
    public Personage(Map<String, Trait> traits){
        this(null, traits);
    }
    
    public Personage(List<Trait> traits) {
        this(null, traits);
    }
    
    /**
     * @return the id of the character
     */
    public String getId(){
        return id;
    }
    
    /**
     * Set the trait of the character. If the character does not have a
     * trait with this name, it is added to the character. Otherwise, the old 
     * trait is discarded and the new is set.
     * 
     * @param trait
     */
    public void setTrait(Trait trait){
        traits.put(trait.getName(), trait);
    }
    
    public List<Trait> getTraits() {
        List<Trait> result = new ArrayList<Trait>();
        
        for( String name: traits.keySet() ){
            result.add(traits.get(name));
        }
        
        return result;
        
    }
    
    /**
     * Get the character's trait that has its name
     * 
     * @param traitName
     * @return trait with name traitName or null if the character doesn't have it
     */
    public Trait getTrait(String traitName){
        return traits.get(traitName);
    }
    
    /**
     * If the character has a trait with name traitName, get the value of this 
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
     * If the character has a trait with name traitName, it sets its value to
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
    
    /**
     * Check if character matches the target traits
     * 
     * For each target, look for a character trait that matches it.
     * 
     * @param targets list of traits to be matched
     * @param strict strict if true, traits values must be identical. Otherwise, character values can be greater.
     * @return true if character meets the requirements
     */
    public boolean match(List<Trait> targets, boolean strict) {
        boolean match = true;
        
        Iterator<Trait> i = targets.iterator();
        while(match && i.hasNext()) {
            Trait target = i.next();
            
            Trait myTrait = getTrait(target.getName());
            
            if(myTrait == null)
                match = false;
            else
                match = myTrait.match(target, strict);
        }
        
        return match;
        
    }
    /**
     * Check if character matches the target traits
     * 
     * For each target, look for a character trait that matches it.
     * 
     * @param targets list of traits to be matched
     * @return true if character meets the requirements
     */
    public boolean match(List<Trait> targets) {
        return match(targets, false);
    }
    
    // Object Overrides
    @Override
    public String toString(){
        StringBuffer text = new StringBuffer("Character: ");
        
        for (String name: traits.keySet()){
            text.append("\n\t"+traits.get(name));
        }
        
        text.append("\n--\n");
        
        return new String(text);
    }
    

}
