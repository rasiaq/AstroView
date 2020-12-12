package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

public class HoroscopeMenuActivity extends AppCompatActivity {

    private final String[] mZodiacSigns = {
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

    private final String[] mHoroscopeTypes = {
            "Today",
            "Week",
            "Month",
            "Year"
    };

    private String mSelectedSign = "Aries";
    private String mSelectedType = "today";

    private SensorManager mSensorManager;
    private SensorEventListener mSensorEventListener;
    private float mAccelCurrent;
    private float mAccelLast;
    private float mShakeVal;
    private static final float MIN_ACCEL_VALUE = 0.2f;

    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorEventListener);
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mSensorManager.registerListener(mSensorEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope_menu);

        // [START zodiac_spinner_configuration]
        ArrayAdapter<String> zodiacAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_selected, mZodiacSigns);
        zodiacAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        final Spinner zodiacSpinner = findViewById(R.id.spinner_zodiac_sign);
        zodiacSpinner.setAdapter(zodiacAdapter);

        zodiacSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedSign = mZodiacSigns[position];
                changeIconOnItemSelected(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // [END zodiac_spinner_configuration]

        // [START horoscope_type_configuration]
        ArrayAdapter<String> horoscopeTypeAdapter = new ArrayAdapter<>(this, R.layout.spinner_item_selected, mHoroscopeTypes);
        horoscopeTypeAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        final Spinner horoscopeTypeSpinner = findViewById(R.id.spinner_horoscope_type);
        horoscopeTypeSpinner.setAdapter(horoscopeTypeAdapter);

        horoscopeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedType = mHoroscopeTypes[position].toLowerCase();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // [END horoscope_type_configuration]

        // [START sensors_configuration]
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mShakeVal = 0.00f;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                System.out.println("Not even here?");
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                mAccelLast = mAccelCurrent;
                mAccelCurrent = (float) Math.sqrt((double) x*x + y*y + z*z);
                float diff = mAccelCurrent - mAccelLast;
                mShakeVal = Math.abs(mShakeVal * 0.9f + diff);
                System.out.println(mShakeVal);

                if (mShakeVal > MIN_ACCEL_VALUE) runGetHoroscope();
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        mSensorManager.registerListener(mSensorEventListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        //[END sensors_configuration]

    }

    public void runGetHoroscope() {
        Intent intent = new Intent(HoroscopeMenuActivity.this, DisplayHoroscopeActivity.class);
        intent.putExtra("sign", mSelectedSign);
        intent.putExtra("type", mSelectedType);
        startActivity(intent);
    }

    public void changeIconOnItemSelected(int position) {
        ImageView zodiacIcon = findViewById(R.id.image_zodiac_icon);
        switch (position) {
            case 0:
                zodiacIcon.setImageResource(R.drawable.ic_aries);
                break;
            case 1:
                zodiacIcon.setImageResource(R.drawable.ic_taurus);
                break;
            case 2:
                zodiacIcon.setImageResource(R.drawable.ic_gemini);
                break;
            case 3:
                zodiacIcon.setImageResource(R.drawable.ic_cancer);
                break;
            case 4:
                zodiacIcon.setImageResource(R.drawable.ic_leo);
                break;
            case 5:
                zodiacIcon.setImageResource(R.drawable.ic_virgo);
                break;
            case 6:
                zodiacIcon.setImageResource(R.drawable.ic_libra);
                break;
            case 7:
                zodiacIcon.setImageResource(R.drawable.ic_scorpio);
                break;
            case 8:
                zodiacIcon.setImageResource(R.drawable.ic_sagittarius);
                break;
            case 9:
                zodiacIcon.setImageResource(R.drawable.ic_capricorn);
                break;
            case 10:
                zodiacIcon.setImageResource(R.drawable.ic_aquarius);
                break;
            case 11:
                zodiacIcon.setImageResource(R.drawable.ic_pisces);
                break;
        }
    }
}