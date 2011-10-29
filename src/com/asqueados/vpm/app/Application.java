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
import com.asqueados.vpm.view.text.PlainDescriptor;

/**
 * Application class is responsible of running the application, or running tests
 * It also includes some utitilties objects
 * 
 * @author Pablo J. Urbano Santos <flamma at member.fsf.org>
 */
public class Application {
    private static final String defaultConfigFile="config.properties";
    private static Configuration configuration;
    private static Configuration dataConfiguration;
    private static Translator translator;
    private static Descriptor textDescriptor;
    private static ColorDescriptor color;
    private static NameGenerator nameGenerator;
    
    public static void init(String configFile) {
        configuration = ConfigurationFactory.createConfiguration(configFile);        
        translator = TranslatorFactory.getTranslator();
        color = ColorDescriptorFactory.getColorDescriptor();
        dataConfiguration = ConfigurationFactory.createConfiguration(
                configuration.getOption("dataConfig"),
                configuration.getOption("dataConfigType")
        );
        nameGenerator = NameGeneratorFactory.createNameGenerator( null, 
                dataConfiguration.getOption("nameGenerator"));
        textDescriptor = new PlainDescriptor(); // TODO: write a factory and use it

    }
    
    public static void init() {        
        init(defaultConfigFile);
    }

    public static ColorDescriptor getColor() {
        if (color==null) init();
        return color;
    }

    public static Configuration getConfiguration() {
        if (configuration==null) init();
        return configuration;
    }

    public static Configuration getDataConfiguration() {
        if (dataConfiguration==null) init();
        return dataConfiguration;
    }

    public static NameGenerator getNameGenerator() {
        if (nameGenerator==null) init();
        return nameGenerator;
    }

    public static Descriptor getTextDescriptor() {
        if (textDescriptor==null) init();
        return textDescriptor;
    }

    public static Translator getTranslator() {
        if (translator==null) init();
        return translator;
    }
    
    
    
    public static void main(String args[]) {
        init();
    }

}
