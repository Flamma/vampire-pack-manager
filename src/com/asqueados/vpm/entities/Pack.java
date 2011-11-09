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
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Class that encapsulates a pack
 * A pack is a group of characters.
 * A pack also has traits. You can set and query for them.
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Pack {
    /**
     * @var characters
     */
    private Map<String, Personage> characters;
    /**
     * @var traits 
     */
    private Map<String, Trait> traits;
    /**
     * @var id UUID of this pack. If not specified, it is auto-generated
     */
    private String id;

    public Pack(String id) {
        characters = new HashMap<String, Personage>();
        traits = new HashMap<String, Trait>();
        
        if(id==null)
            this.id = UUID.randomUUID().toString();
        else
            this.id = id;
    }

    
    public Pack(){
        this(null);
    }
        
    /**
     * @return the id of the character
     */
    public String getId(){
        return id;
    }
    
    /**
     * Add character to pack
     * 
     * @param character
     */
    public void addCharacter(Personage character) {
        characters.put(character.getId(), character);
    }
    
    /**
     * Remove previous characters and add new characters to pack
     * @param characters
     */
    public void setCharacters(List<Personage> characters) {
        this.characters.clear();
        
        for(Personage character: characters) 
            addCharacter(character);
            
    }
    /**
     * Return the character with the specified id, or null if it doesn't exist
     * @param id
     * @return the character with the specified id, or null if it doesn't exist
     */
    public Personage getCharacter(String id) {
        return characters.get(id);
    }
    
    /**
     * Get a list of characters matching the targets
     * 
     * @param targets list of traits to be matched
     * @param strict if numerical values must be equal, or greater or equal
     * @return list of characters matching the targets
     */
    public List<Personage> searchCharacters(List<Trait> targets, boolean strict) {
        List<Personage> matched = new ArrayList<Personage>();
        
        for(Personage character: characters.values()) {
            if (character.match(targets, strict)) {
                matched.add(character);
            }
        }
        
        return matched;
    }
    
    /**
     * @return List with all the characters of the pack
     */
    public List<Personage> getCharacters() {
        return new ArrayList<Personage>(characters.values());
    }
    
    /**
     * Get a list of characters matching the targets
     * 
     * @param targets list of traits to be matched
     * @return list of characters matching the targets
     */
    public List<Personage> searchCharacters(List<Trait> targets) {
        return searchCharacters(targets, false);
    }
    
    
    
    /**
     * Set the trait of the pack. If the pack does not have a
     * trait with this name, it is added to the pack. Otherwise, the old 
     * trait is discarded and the new is set.
     * 
     * @param trait
     */
    public void setTrait(Trait trait){
        traits.put(trait.getName(), trait);
    }
    
    /**
     * Set each trait of the list
     * 
     * Do not remove other traits
     * 
     * @param traits
     */
    public void setTraits(List<Trait> traits) {
        for (Trait trait: traits) {
            setTrait(trait);
        }
    }
    
    public List<Trait> getTraits() {
        List<Trait> result = new ArrayList<Trait>();
        
        for( String name: traits.keySet() ){
            result.add(traits.get(name));
        }
        
        return result;
        
    }
    
    /**
     * Get the pack's trait that has its name
     * 
     * @param traitName
     * @return trait with name traitName or null if the character doesn't have it
     */
    public Trait getTrait(String traitName){
        return traits.get(traitName);
    }
    
    /**
     * If the pack has a trait with name traitName, get the value of this 
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
     * If the pack has a trait with name traitName, it sets its value to
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
        StringBuffer text = new StringBuffer("Pack: ");

        for (String name: characters.keySet()){
            text.append("\n\t"+characters.get(name));
        }

        for (String name: traits.keySet()){
            text.append("\n\t"+traits.get(name));
        }
        
        text.append("\n--\n");
        
        return new String(text);
    }
    

}
