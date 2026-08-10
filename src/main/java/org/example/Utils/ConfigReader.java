package org.example.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {


    private static String read(String key) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("config.properties"));
             InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
            properties.load(reader);
        } catch (IOException e) {
            System.out.println("config.properties file loading error: " + e.getMessage());
        }
        return properties.getProperty(key);
    }


    public static String get(String key) {
        return read(key);
    }

    public static Long getLong(String key){
        return  Long.parseLong(read(key));
    }




}
