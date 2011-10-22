/*
 * ArchetypeFactoryTest.java
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

import com.asqueados.vpm.app.Application;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class ArchetypeFactoryTest {

    public ArchetypeFactoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Application.init();
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of getArchetypes method, of class ArchetypeFactory.
     */
    @Test
    public void getArchetypes() throws Exception {
        System.out.println("TEST ArchetypeFactory");        
        System.out.println("getArchetypes");
        
        Collection<Archetype> result = ArchetypeFactory.getArchetypes();
        assertNotNull(result);
        assertNotSame(0 , result.size());
        
        for( Archetype archetype: result ) {
            System.out.println(archetype);
            assertNotNull(archetype);
            assertTrue(archetype instanceof Archetype);
        }
    }

}