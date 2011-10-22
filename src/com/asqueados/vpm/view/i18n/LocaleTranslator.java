/*
 * Translator.java
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

package com.asqueados.vpm.view.i18n;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for doing translation, without having to use long lines, or too many
 * static references to locale path
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class LocaleTranslator implements Translator {
    private ResourceBundle bundle;
    
    public LocaleTranslator () {
        bundle = java.util.ResourceBundle.
            getBundle("com/asqueados/vpm/view/i18n/locale");        
    }

    /**
     * Get the i18ned string for the key
     * 
     * @param key
     * @return i18ned string for the key
     */
    public String translate(String key) {
        String translation;
        try {
            translation = bundle.getString(key);
        } catch (java.util.MissingResourceException ex) {
            Logger.getLogger(LocaleTranslator.class.getName()).log(Level.SEVERE, null, ex);
            translation = '*'+key+'*';
        }
        
        return translation;
    }

}
