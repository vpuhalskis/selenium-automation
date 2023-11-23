package lv.acodemy.utils;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ConfigurationProperties {

    public static PropertiesConfiguration getConfig(){

        //return new Configurations().properties("configuration.properties"); verni v etom metode fail configuration.properties
        // surround with try catch

        try {
            return new Configurations().properties("configuration.properties");
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }


    }
}

//try{catch}