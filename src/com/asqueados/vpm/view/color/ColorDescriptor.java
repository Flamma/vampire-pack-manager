/*
 * ColorDescriptor.java
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

package com.asqueados.vpm.view.color;

import com.asqueados.vpm.entities.Trait;
import java.awt.Color;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public interface ColorDescriptor {
    /**
     * Defines colour based on trait.
     * (e.g: red for low traits and green for high ones)
     * 
     * @param trait
     * @return colour based on trait
     */
    public Color getTraitValueColor(Trait trait);
}
