package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DisplayHoroscopeActivity extends AppCompatActivity {

    private static final String BASE_REQUEST_URL = "http://horoscope-api.herokuapp.com/horoscope/";
    private String mResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_bar);
        makeRequest();
    }

    private void makeRequest() {
        String sign = getIntent().getStringExtra("sign");
        String type = getIntent().getStringExtra("type");
        String requestURL = BASE_REQUEST_URL + type + "/" + sign;
        String[] data = new String[2];

        final OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(requestURL)
                .get()
                .build();
        try {
            getResponse(client, request);
            data = extractData(type);
        } catch (InterruptedException | JSONException e) {
            e.printStackTrace();
        }

        setView(sign, data[0], data[1]);
    }

    private void getResponse(final OkHttpClient okHttpClient, final Request request) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Response resp;
                try {
                    resp = okHttpClient.newCall(request).execute();
                    if (resp.body() != null) {
                        mResponse = resp.body().string();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        thread.join();
    }

    private String[] extractData(String type) throws JSONException {
        String[] values = new String[2];
        JSONObject jsonObject = new JSONObject(mResponse);
        if (type.equals("today")) {
            values[0] = jsonObject.getString("date");
        } else {
            values[0] = jsonObject.getString(type);
        }
        values[1] = jsonObject.getString("horoscope");
        return values;
    }

    private void setView(String sign, String dates, String horoscope) {
        setContentView(R.layout.activity_display_horoscope);
        TextView signTV = findViewById(R.id.signTV);
        signTV.setText(sign);
        TextView datesTV = findViewById(R.id.datesTV);
        datesTV.setText(dates);
        TextView horoscopeTV = findViewById(R.id.horoscopeTextView);
        horoscopeTV.setText(horoscope);
    }


    public void backToMenu(View view) {
        Intent intent = new Intent(DisplayHoroscopeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}