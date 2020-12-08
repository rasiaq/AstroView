package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetHoroscope extends AppCompatActivity {

    private final String baseRequestURL = "http://horoscope-api.herokuapp.com/horoscope/";
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_horoscope);
        String sign = getIntent().getStringExtra("sign");
        String type = getIntent().getStringExtra("type");
        String requestURL = baseRequestURL + type + "/" + sign;

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(requestURL)
                .get()
                .build();
        try {
            getResponse(client, request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(response);
        try {
            parseResponse();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getResponse(final OkHttpClient okHttpClient, final Request request) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Response resp;
                try {
                    resp = okHttpClient.newCall(request).execute();
                    if (resp.body() != null) {
                        response = resp.body().string();
                        System.out.println("Zakończono");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    public void parseResponse() throws JSONException {
//        Response wygląda inaczej dla daily i inaczej dla pozostałych
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.names();
        String key = jsonArray.getString(0);
        String value = jsonObject.getString(key);
        System.out.println(key + " " + value);
    }
}