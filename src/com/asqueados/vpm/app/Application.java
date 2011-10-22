/*
 * Application.java
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

package com.asqueados.vpm.app;

import com.asqueados.vpm.configuration.Configuration;
import com.asqueados.vpm.configuration.ConfigurationFactory;
import com.asqueados.vpm.view.color.ColorDescriptor;
import com.asqueados.vpm.view.color.ColorDescriptorFactory;
import com.asqueados.vpm.view.i18n.Translator;
import com.asqueados.vpm.view.i18n.TranslatorFactory;
import com.asqueados.vpm.view.text.Descriptor;
import com.asqueados.vpm.view.text.NameGenerator;
import com.asqueados.vpm.view.text.NameGeneratorFactory;

/**
 * Application class is responsible of running the application, or running tests
 * It also includes some utitilties objects
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Application {
    public static Configuration configuration;
    public static Configuration dataConfiguration;
    public static Translator translator;
    public static Descriptor text;
    public static ColorDescriptor color;
    public static NameGenerator nameGenerator;
    
    public static void init() {
        translator = TranslatorFactory.getTranslator();
        color = ColorDescriptorFactory.getColorDescriptor();
        configuration = ConfigurationFactory.createConfiguration("config.properties");
        dataConfiguration = ConfigurationFactory.createConfiguration(
                configuration.getOption("dataConfig"),
                configuration.getOption("dataConfigType")
        );
        nameGenerator = NameGeneratorFactory.createNameGenerator( null, 
                dataConfiguration.getOption("nameGenerator"));

    }
    
    public static void main(String args[]) {
        init();
    }

}
