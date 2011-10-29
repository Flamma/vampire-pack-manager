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

import com.asqueados.vpm.app.Application;
import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.Trait;
import com.asqueados.vpm.view.TraitTypes;
import java.util.List;

/**
 * Class with static methods to provide text descriptions
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class PlainDescriptor implements Descriptor {
    /**
     * Get a text description of the value of the trait. Some traits are treated individually, by name, while the rest have generic descriptions
     * 
     * FIXME: This function is a stub. Should be written more seriously when things are more definitive
     * 
     * @param trait
     * @return a text description of the value of the trait.
     */
    public String getTraitValueDescription(Trait trait) {
        String description=null;
        
        if (trait==null) return null;
        
        if (trait.getName().equals("sex")) {
            description = getTranslation((String)trait.getValue());
        } else if (trait.getName().equals("physique") ){            
            if ( (Integer)trait.getValue() < 5)
                description = Application.getTranslator().translate("weak");
            else if ((Integer)trait.getValue() > 7 && (Integer)trait.getValue() < 10)
                description = Application.getTranslator().translate("strong");
            else if  ( (Integer)trait.getValue() >= 10)
                description = Application.getTranslator().translate("formidable");
        } else if (trait.getName().equals("social") ){            
            if ( (Integer)trait.getValue() < 5)
                description = Application.getTranslator().translate("shy");
            else if (((Integer)trait.getValue() > 7 && (Integer)trait.getValue() < 10))
                description = Application.getTranslator().translate("charismatic");
            else if  ( (Integer)trait.getValue() >= 10)
                description = Application.getTranslator().translate("magnetic");
        } else if (trait.getName().equals("mental") ){
            if ( (Integer)trait.getValue() < 5)
                description = Application.getTranslator().translate("dumb");
            else if (((Integer)trait.getValue() > 7 && (Integer)trait.getValue() < 10))
                description = Application.getTranslator().translate("smart");
            else if  ( (Integer)trait.getValue() >= 10) 
                description = Application.getTranslator().translate("genius");
        } else {
            if ( trait.getType().equals(Trait.INTEGER) ) {
                if ( (Integer)trait.getValue() > 0 && (Integer)trait.getValue() < 5)
                    description = Application.getTranslator().translate("novice");
                else if ((Integer)trait.getValue() >= 5 && (Integer)trait.getValue() < 10)
                    description = Application.getTranslator().translate("good");
                else if  ( (Integer)trait.getValue() >= 10)
                    description = Application.getTranslator().translate("superb");
            }
        }
   
        return description;
    }
    
    /**
     * Returns a description of the character type (e.g: vampire, human)
     * 
     * Race can be obtained from several taits
     * 
     * @param personage
     * @return a description of the character type (e.g: vampire, human)
     */
    public String getRaceDescription(Personage personage) {
        Trait vtrait = personage.getTrait("vampire"); 
        if( vtrait!=null && (Boolean) vtrait.getValue() )
            return Application.getTranslator().translate("vampire");
        else
            return Application.getTranslator().translate("human");
    }
    /**
     * Returns a description of the attributes of a character
     * 
     * @param personage
     * @return a description of the attributes of a character
     */        
    public String getPersonageAttributesDescription(Personage character) {
        StringBuffer description = new StringBuffer();
        
        List<String> attributeNames= TraitTypes.getAttributesNames();
        
        for( String name: attributeNames) {
            String adjective = getTraitValueDescription(character.getTrait(name));
            
            if(adjective != null) {
                if(description.length() > 0)
                    description.append(", ");
                description.append(adjective);
            }
        }
        
        
        return new String(description);
    }
    
    public String getPersonageIntroDescription(Personage personage) {
        StringBuffer description=new StringBuffer();
        // FIXME: No i18n
        description.append(personage.getTrait("name").getValue());
        description.append(" is a ");
        
        String attDescription = getPersonageAttributesDescription(personage);
        description.append(attDescription);
        if (attDescription.length()>0) description.append(", ");
        
        
        description.append(getTraitValueDescription(personage.getTrait("sex")));
        description.append(" ");
        description.append(getRaceDescription(personage));
        description.append("."+nl());
        
        return new String(description);

    }
    
    public String getPersonageSkillsDescription(Personage personage) {
        StringBuffer description=new StringBuffer();

        // TODO: i18n
        description.append(nl()+"SKILLS:"+nl());
        List<String> skillsNames= TraitTypes.getSkillsNames();
        for(String name: skillsNames) {
            Trait trait = personage.getTrait(name);
            
            if( trait != null ) {
                String traitName = Application.getTranslator().translate(name);
                String traitDescription = getTraitValueDescription(trait);
                
                if(traitDescription != null)
                    description.append(String.format("%-15s:%15s"+nl(), traitName, traitDescription));
            }
            
        }
        
        return new String(description);        
    }

    public String getPersonageDescription(Personage personage) {
        StringBuffer description=new StringBuffer();
        
        description.append(getPersonageIntroDescription(personage));
        description.append(getPersonageSkillsDescription(personage));
                
        return new String(description);
    }
    
    /**
     * 
     * @return character of new line
     */
    protected String nl() {
        return "\n";
    }

    

    private static String getTranslation(String string) {
        return Application.getTranslator().translate(string);
    }
    
}
