package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DisplayHoroscope extends AppCompatActivity {

    private String header = "Okay, here is what we've got";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_horoscope);
        String sign = getIntent().getStringExtra("sign");
        String dates = getIntent().getStringExtra("dates");
        String horoscope = getIntent().getStringExtra("horoscope");
        setTextViews(sign, dates, horoscope);
    }

    public void setTextViews(String sign, String dates, String horoscope) {
        TextView headerTV = findViewById(R.id.headerTextView);
        headerTV.setText(header);

        TextView signTV = findViewById(R.id.signTV);
        signTV.setText(sign);

        TextView datesTV = findViewById(R.id.datesTV);
        datesTV.setText(dates);

        TextView horoscopeTV = findViewById(R.id.horoscopeTextView);
        horoscopeTV.setText(horoscope);
    }

    public void backToMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}