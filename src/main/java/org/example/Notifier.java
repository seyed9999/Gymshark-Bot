package org.example;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

public class Notifier {
    private final String URL = System.getenv("URL");
    private OkHttpClient client = new OkHttpClient();

    public void sendMsg(String msg) {
        String json = "{\"text\": \"" + msg + "\"}";
        MediaType type = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(type, json);
        Request request = new Request.Builder().url(URL).post(body).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
