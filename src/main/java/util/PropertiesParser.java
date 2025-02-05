package util;

import exception.PropertyException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertiesParser {
    private Properties properties;

    public PropertiesParser(String propertyFileName) throws PropertyException {
        this.properties = new Properties();
        readProperties(propertyFileName);
    }

    private void readProperties(String propertyFileName) throws PropertyException {

        try (InputStream inputStream = PropertiesParser.class.getClassLoader().getResourceAsStream(propertyFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Error! Class: " + PropertiesParser.class.getName() +
                    ". Date: " + new Date() + ". Message: " + e.getMessage());
            throw new PropertyException("Unable to find application.properties: " + propertyFileName + e.getMessage());
        }
    }

    public String getPropertyByName(String str) {
        return properties.getProperty(str);
    }
}
