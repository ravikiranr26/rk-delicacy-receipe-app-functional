package com.selenium.test.configuration.properties;

import com.selenium.test.configuration.TestsConfig;
import com.selenium.test.exceptions.TestsConfigurationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesLoader {

    /**
     * Sets TestConfig object fields values to specified properties values
     *
     * @param config {@link com.selenium.test.configuration.TestsConfig} object
     */
    public static void populate(TestsConfig config) {
        Properties properties = null;
        String propertyFilePath = System.getProperty("user.dir")+
                "\\src\\test\\resources\\config.properties";
        PropertyFile propertyFileAnnotation = config.getClass().getAnnotation(PropertyFile.class);
        if (propertyFileAnnotation != null) {
            String propertyFileName = propertyFileAnnotation.value();
            if (propertyFileName == null) {
                throw new TestsConfigurationException("Property file name cannot be empty. Class name : " + config.getClass().getName());
            } else {
                properties = new Properties();
                try {
                    InputStream stream = new FileInputStream(propertyFilePath);
                    if(stream != null) {
                        properties.load(stream);
                    } else {
                        throw new TestsConfigurationException("Unable to read property file with name '" + propertyFileName + "' - file not found");
                    }
                } catch (IOException e) {
                    throw new TestsConfigurationException("Error while reading property file with name '" + propertyFileName + "' : " + e.getMessage(), e);
                }
            }
        } else {
            properties = System.getProperties();
        }
        Field[] fields = config.getClass().getDeclaredFields();
        for (Field field : fields) {
            Property propertyAnnotation = field.getAnnotation(Property.class);
            if (propertyAnnotation != null) {
                String propertyName = propertyAnnotation.value();
                if (propertyName == null) {
                    throw new TestsConfigurationException("Property value cannot be empty. Field name : " + field.getName());
                }
                String propertyValue = properties.getProperty(propertyName);
                if (propertyValue != null) {
                    try {
                        field.setAccessible(true);
                        field.set(config, propertyValue);
                    } catch (IllegalAccessException e) {
                        throw new TestsConfigurationException("Field cannot be set...", e);
                    }
                }
            }
        }
    }

}
