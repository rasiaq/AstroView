package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class AuthorInfoActivity extends AppCompatActivity {

    private static String GITHUB_PROFILE_LINK = "https://github.com/rasiaq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author_info);
    }

    public void runGitHubProfile(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_PROFILE_LINK));
        startActivity(intent);
    }

    public void backToMenu(View v) {
        finish();
    }
}