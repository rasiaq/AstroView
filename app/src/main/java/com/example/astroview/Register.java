package com.example.astroview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private EditText newEmail;
    private EditText newPassword;
    private EditText confPassword;
    private TextView wrongPasswordTV;
    private Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newEmail = findViewById(R.id.newEmail);
        newPassword = findViewById(R.id.newPassword);
        confPassword = findViewById(R.id.confPassword);
        wrongPasswordTV = findViewById(R.id.wrongPasswordTV);
        signUpBtn.setEnabled(false);

        confPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String pass1 = newPassword.getText().toString();
                String pass2 = confPassword.getText().toString();
                if (!pass1.equals(pass2)) {
                    wrongPasswordTV.setText("Passwords don't match");
                } else {
                    signUpBtn.setEnabled(true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}