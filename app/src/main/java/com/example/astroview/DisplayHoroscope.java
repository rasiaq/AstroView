package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DisplayHoroscope extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_horoscope);
        String sign = getIntent().getStringExtra("sign");
        String horoscope = getIntent().getStringExtra("horoscope");
    }
}