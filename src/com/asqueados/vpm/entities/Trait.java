/*
 * Trait.java
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

/**
 * This class represents a generic trait of the character (name, attribute, skill)
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Trait {
    // static constants
    public final static String OBJECT= "object";
    public final static String INTEGER= "integer";
    public final static String STRING= "string";
    public final static String BOOLEAN="boolean";

    // members
    private String name;
    private String type;
    private Object value;
    private Integer cost;
    
    // Constructors
    public Trait(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public Trait(String name, String type, Object value) {
        this(name, type);
        this.value = value;
    }

    public Trait(String name, String type, Object value, Integer cost) {
        this(name, type, value);
        this.cost = cost;
    }
    
    // Static methods
    
    /**
     * Guess the type of the trait from the value class
     * 
     * @param value
     * @return string with the name of the type
     */
    public static String getTypeFromValue(Object value) {
        if (value == null)
            return null;
        if (value instanceof Integer)
            return INTEGER;
        if (value instanceof String)
            return STRING;
        if (value instanceof Boolean)
            return BOOLEAN;
        
        return OBJECT;

    }

    // Getters and setters
    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
    
    // Other methods
    public boolean isNumeric() {
        if(type.equals(INTEGER))
            return true;
        
        return false;
    }
    
    public void inc() {
        inc(1);
    }
    
    public void inc(Integer amount) {
        if(type.equals(INTEGER)) {
            value = ((Integer)value) + amount;
        }
    }
    
    // Object Overrides
    @Override
    public String toString(){
        return "trait: "+name+"("+type+")="+value;
    }
    
    
}
