package com.interpretation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;

import com.loader.EnvLoader;

public class Translator {

    private static final Map<String, String> envVariables = EnvLoader.loadEnvVariables();

    public static String translate (String text) throws Exception {
        HttpURLConnection con = (HttpURLConnection) new URL(envVariables.get("OPEN_AI_URL")).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + envVariables.get("API_KEY"));

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());

        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        String response = new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text");
        
        return response;
    }
}