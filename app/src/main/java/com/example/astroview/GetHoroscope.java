package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetHoroscope extends AppCompatActivity {

    private final int splashTime = 1300;

    private final String baseRequestURL = "http://horoscope-api.herokuapp.com/horoscope/";
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_horoscope);
        String sign = getIntent().getStringExtra("sign");
        String type = getIntent().getStringExtra("type");
        String requestURL = baseRequestURL + type + "/" + sign;

        String[] horoscopeData = new String[2];

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(requestURL)
                .get()
                .build();
        try {
            getResponse(client, request);
            horoscopeData = parseResponse(type);
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        runDisplayHoroscope(horoscopeData, sign, type);
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
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    public String[] parseResponse(String type) throws JSONException {
        String[] values = new String[2];
        JSONObject jsonObject = new JSONObject(response);
        if (type.equals("today")) {
            values[0] = jsonObject.getString("date");
        } else {
            values[0] = jsonObject.getString(type);
        }
        values[1] = jsonObject.getString("horoscope");
        return values;
    }

    public void runDisplayHoroscope(String[] horoscopeData, String sign, String type) {
        final Intent intent = new Intent(this, DisplayHoroscope.class);
        intent.putExtra("sign", sign);
        intent.putExtra("dates", horoscopeData[0]);
        System.out.println(horoscopeData[0]);
        intent.putExtra("horoscope", horoscopeData[1]);
        System.out.println(horoscopeData[1]);
        Handler handler = new Handler(); // for visual purpose only
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(intent);
            }
        }, splashTime);
    }
}