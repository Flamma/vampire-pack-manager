/*
 * HtmlDescriptor.java
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
import java.awt.Color;
import java.util.List;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class HtmlDescriptor extends PlainDescriptor {
    /**
     * Returns a description of the attributes of a character
     * 
     * @param personage
     * @return a description of the attributes of a character
     */        
    @Override
    public String getPersonageAttributesDescription(Personage character) {
        StringBuffer description = new StringBuffer();
        
        List<String> attributeNames= TraitTypes.getAttributesNames();
        
        for( String name: attributeNames) {
            String adjective = getTraitValueDescription(character.getTrait(name));
            
            if(adjective != null) {
                if(description.length() > 0)
                    description.append(", ");
                Color color = Application.getColor().getTraitValueColor(character.getTrait(name));
                if ( color != null )
                    description.append("<span style='color:"+getTraitValueColorHTML(color)+"'>");
                description.append(adjective);
                if ( color != null )
                    description.append("</span>");
            }
        }
        
        return new String(description);
    }
    
    @Override
    public String getTraitValueDescription(Trait trait) {
        String htmlDescription;
        String plainDescription = super.getTraitValueDescription(trait);
        
        if(plainDescription == null) {
            htmlDescription = null;
        } else {
            Color color = Application.getColor().getTraitValueColor(trait);
            if (color==null) {
                htmlDescription = plainDescription;
            } else {
                String htmlColor = getTraitValueColorHTML(color);
                htmlDescription = 
                        "<span style='color:"+htmlColor+"'>" +
                        plainDescription +
                        "</span>";
            }
        }
        
        return htmlDescription;

    }
    
    @Override
    protected String nl() {
        return "<br/>\n";
    }
    
    private static String getTraitValueColorHTML(Color color) {        
        if ( color != null )
            return "#" + Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
        
        return "#000000";
    }    
}
