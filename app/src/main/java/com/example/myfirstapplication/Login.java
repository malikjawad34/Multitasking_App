package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private EditText editLoginEmail, editLoginPassword;
    private Button loginButton;
    private FirebaseAuth auth;
    private TextView GoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        editLoginEmail = findViewById(R.id.loginEmail);
        editLoginPassword = findViewById(R.id.loginPassword);
        loginButton = findViewById(R.id.loginBtn);
        GoToRegister = findViewById(R.id.goToRegister);

        GoToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regEmail = editLoginEmail.getText().toString().trim();
                String regPassword = editLoginPassword.getText().toString().trim();

                if(!regEmail.isEmpty())
                {
                    if(!regPassword.isEmpty())
                    {
                        auth.signInWithEmailAndPassword(regEmail,regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(Login.this,PrayerTimeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    task.addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Login.this,"Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }
                        });
                    }
                    else{
                        Snackbar.make(loginButton,"Invalid Password", Snackbar.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Login.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String email = sh.getString("loginEmail", "");

        editLoginEmail.setText(email);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("loginEmail", editLoginEmail.getText().toString());
        myEdit.commit();
    }
}

