package com.loader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class EnvLoader {
    
    public static Map<String, String> loadEnvVariables() {
        Map<String, String> envVariables = new HashMap<>();
        Path envFilePath = Paths.get("./src/main/resources/.env");

        try (Stream<String> lines = Files.lines(envFilePath)) {
            lines.forEach(line -> {
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    envVariables.put(key, value);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return envVariables;
    }
}
