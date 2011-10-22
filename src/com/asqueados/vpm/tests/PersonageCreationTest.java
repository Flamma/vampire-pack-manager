/*
 * PersonageCreationTest.java
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

package com.asqueados.vpm.tests;

import com.asqueados.vpm.entities.Personage;
import com.asqueados.vpm.entities.PersonageFactory;
import com.asqueados.vpm.exceptions.UnableToCreatePersonageException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @deprecated
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
@Deprecated
public class PersonageCreationTest {
    public static void main(String args[]) {
        try {
            Personage character = PersonageFactory.createCharacter();
            
            System.out.println(character);
            
        } catch (UnableToCreatePersonageException ex) {
            Logger.getLogger(PersonageCreationTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
