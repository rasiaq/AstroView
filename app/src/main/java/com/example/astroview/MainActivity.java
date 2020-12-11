package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void runHoroscopeMenu(View v) {
        Intent intent = new Intent(this, HoroscopeMenu.class);
        startActivity(intent);
    }

    public void signOut(View v) {
        Intent intent = new Intent(this, Login.class);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
        finish();
    }

    public void quit(View v) {
        finishAndRemoveTask();
    }
}