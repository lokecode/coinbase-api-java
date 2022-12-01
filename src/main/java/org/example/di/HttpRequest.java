package org.example.di;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class HttpRequest {
    public static String Request(String urlToRead) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(urlToRead).openConnection();
        conn.setRequestMethod("GET");
        List<String> response = new BufferedReader(new InputStreamReader(conn.getInputStream())).lines().toList();
        StringBuilder ss = new StringBuilder();
        response.forEach(ss::append);

        return ss.toString();
    }
}

