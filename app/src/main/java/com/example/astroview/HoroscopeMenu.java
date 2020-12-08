package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class HoroscopeMenu extends AppCompatActivity {

    private final String[] zodiacSigns = {
            "Aries",
            "Taurus",
            "Gemini",
            "Cancer",
            "Leo",
            "Virgo",
            "Libra",
            "Scorpio",
            "Sagittarius",
            "Capricorn",
            "Aquarius",
            "Pisces"
    };

    private final String[] horoscopeTypes = {
            "Today",
            "Week",
            "Month",
            "Year"
    };

    private String selectedSign = "Aries";
    private String selectedType = "today";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_menu);

        ArrayAdapter<String> zodiacAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_selected, zodiacSigns);
        zodiacAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        final Spinner zodiacSpinner = findViewById(R.id.zodiacSignSpinner);
        zodiacSpinner.setAdapter(zodiacAdapter);

        zodiacSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSign = zodiacSigns[position];
                changeIconOnItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> horoscopeTypeAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_selected, horoscopeTypes);
        horoscopeTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        final Spinner horoscopeTypeSpinner = findViewById(R.id.horoscopeTypeSpinner);
        horoscopeTypeSpinner.setAdapter(horoscopeTypeAdapter);

        horoscopeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = horoscopeTypes[position].toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void runGetHoroscope(View v) {
        Intent intent = new Intent(this, GetHoroscope.class);
        intent.putExtra("sign", selectedSign);
        intent.putExtra("type", selectedType);
        startActivity(intent);
    }

    public void changeIconOnItemSelected(int position) {
        ImageView zodiacIcon = findViewById(R.id.zodiacIconImage);
        switch (position) {
            case 0:
                zodiacIcon.setImageResource(R.drawable.icon_aries);
                break;
            case 1:
                zodiacIcon.setImageResource(R.drawable.icon_taurus);
                break;
            case 2:
                zodiacIcon.setImageResource(R.drawable.icon_gemini);
                break;
            case 3:
                zodiacIcon.setImageResource(R.drawable.icon_cancer);
                break;
            case 4:
                zodiacIcon.setImageResource(R.drawable.icon_leo);
                break;
            case 5:
                zodiacIcon.setImageResource(R.drawable.icon_virgo);
                break;
            case 6:
                zodiacIcon.setImageResource(R.drawable.icon_libra);
                break;
            case 7:
                zodiacIcon.setImageResource(R.drawable.icon_scorpio);
                break;
            case 8:
                zodiacIcon.setImageResource(R.drawable.icon_sagittarius);
                break;
            case 9:
                zodiacIcon.setImageResource(R.drawable.icon_capricorn);
                break;
            case 10:
                zodiacIcon.setImageResource(R.drawable.icon_aquarius);
                break;
            case 11:
                zodiacIcon.setImageResource(R.drawable.icon_pisces);
                break;
        }
    }
}