/*
 * TaskHunt.java
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
 * The hunt task
 * 
 * @todo This class is right now only for testing
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class TaskHunt extends Task {

    @Override
    public Object doTask() {
        Object result = null;
        
        for ( Personage character : characters ) {
            Integer hunt = (Integer) character.getValue("hunt");
            Integer blood = (Integer) character.getValue("blood");
            Integer bloodpool = (Integer) character.getValue("bloodpool");
            
            if ( bloodpool != null && bloodpool > 0 ){
                if (blood == null) blood = 0;
                
                if (hunt == null) hunt = -5;
            }
            Integer bloodAchieved = new Integer( ""+ (Math.round( Math.random() * time * hunt/10.0 )));
            System.out.println("blood: "+blood+"; blood achieved: "+bloodAchieved);
            
            blood += bloodAchieved;
            
            if(blood > bloodpool)
                blood = bloodpool;
            System.out.println("blood: "+blood);
            character.setValue("blood", blood);
        }
        
        return result;
    }
    

}
