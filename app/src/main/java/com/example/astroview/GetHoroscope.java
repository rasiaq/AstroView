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

    private final int splashTime = 1500;

    private final String baseRequestURL = "http://horoscope-api.herokuapp.com/horoscope/";
    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_horoscope);
        String sign = getIntent().getStringExtra("sign");
        String type = getIntent().getStringExtra("type");
        String requestURL = baseRequestURL + type + "/" + sign;

        String horoscopeResult = "";

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(requestURL)
                .get()
                .build();
        try {
            getResponse(client, request);
            horoscopeResult = parseResponse();
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        runDisplayHoroscope(horoscopeResult, sign);
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

    public String parseResponse() throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        return jsonObject.getString("horoscope");
    }

    public void runDisplayHoroscope(String horoscope, String sign) {
        final Intent intent = new Intent(this, DisplayHoroscope.class);
        intent.putExtra("sign", sign);
        System.out.println(horoscope);
        intent.putExtra("horoscope", horoscope);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                startActivity(intent);
            }
        }, splashTime);
    }
}