package ru.amerain.mpkpizza.data.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Settings {

    private static Settings instance = new Settings();

    private Properties properties = new Properties();

    public static Settings getInstance() {
        return instance;
    }

    private Settings() {
        try {
            properties.load
                    (new FileInputStream
                            (getClass().getClassLoader().getResource("inform.properties").getFile())
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return properties.getProperty(key);
    }
}
