package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


public class DisplayHoroscope extends AppCompatActivity {

    private String header = "Okay, here is what we've got for you, ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_horoscope);
        String sign = getIntent().getStringExtra("sign");
        String horoscope = getIntent().getStringExtra("horoscope");
        header += sign;
        setTextViews(sign, horoscope);
    }

    public void setTextViews(String sign, String horoscope) {
        TextView headerTV = findViewById(R.id.headerTextView);
        headerTV.setText(header);
        TextView horoscopeTV = findViewById(R.id.horoscopeTextView);
        horoscopeTV.setText(horoscope);
    }
}