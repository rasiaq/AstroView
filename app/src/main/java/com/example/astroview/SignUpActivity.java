package com.example.astroview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mEditTextNewEmail;
    private EditText mEditTextNewPassword;
    private EditText mEditTextConfPassword;
    private TextView mTextViewPassword;
    private Button mButtonSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        mEditTextNewEmail = findViewById(R.id.input_new_email);
        mEditTextNewPassword = findViewById(R.id.input_new_password);
        mEditTextConfPassword = findViewById(R.id.input_conf_password);
        mTextViewPassword = findViewById(R.id.text_wrong_password);
        mButtonSignIn = findViewById(R.id.button_sign_up);
        mButtonSignIn.setEnabled(false);

        mEditTextConfPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String pass1 = mEditTextNewPassword.getText().toString();
                String pass2 = mEditTextConfPassword.getText().toString();
                if (!pass1.equals(pass2)) {
                    mTextViewPassword.setText("Passwords don't match");
                    mButtonSignIn.setEnabled(false);
                    if (!mButtonSignIn.isEnabled()) mButtonSignIn.setEnabled(false);
                } else {
                    mTextViewPassword.setText("");
                    mButtonSignIn.setEnabled(true);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void signUp(View view) {
        String email = mEditTextNewEmail.getText().toString().trim();
        System.out.println(email);
        String password = mEditTextNewPassword.getText().toString();
        System.out.println(password);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateUI();
                        } else {
                            if (task.getException() != null) {
                                Toast.makeText(SignUpActivity.this,
                                        task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void updateUI() {
        final Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Registration was successful - you will be logged in now!")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(intent);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}