package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText editRegisterEmail, editRegisterPassword, editRegisterCity, editRegisterName, editRegisterPhone;
    private Button registerButton;
    private FirebaseAuth auth;
    private TextView GOTOLogin;
    private FirebaseFirestore FFAuth;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        FFAuth = FirebaseFirestore.getInstance();

        register = findViewById(R.id.tvRegister);
        registerForContextMenu(register);
        editRegisterEmail = findViewById(R.id.registerEmail);
        editRegisterPassword = findViewById(R.id.registerPassword);
        editRegisterCity = findViewById(R.id.registerCity);
        editRegisterName = findViewById(R.id.registerName);
        editRegisterPhone = findViewById(R.id.registerPhone);
        registerButton = findViewById(R.id.registerBtn);
        GOTOLogin = findViewById(R.id.goTologin);

        GOTOLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String regEmail = editRegisterEmail.getText().toString().trim();
                String regPassword = editRegisterPassword.getText().toString().trim();

                if(!regEmail.isEmpty())
                {
                    if(!regPassword.isEmpty())
                    {
                          auth.createUserWithEmailAndPassword(regEmail,regPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    DocumentReference documentReference = FFAuth.collection("Users").document(auth.getCurrentUser().getUid());
                                    Map<String, Object> user = new HashMap<>();
                                    user.put("email", regEmail);
                                    user.put("password", regPassword);
                                    user.put("name", editRegisterName.getText().toString().trim());
                                    user.put("city", editRegisterCity.getText().toString().trim());
                                    user.put("phone", editRegisterPhone.getText().toString().trim());
                                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Register.this, "Success", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    Intent intent = new Intent(Register.this,PrayerTimeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    task.addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Register.this,"Error : " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                              }
                          });
                    }
                    else{
                        Toast.makeText(Register.this, "Invalid Password", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(Register.this, "Invalid Email", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater contextMenu = getMenuInflater();
        contextMenu.inflate(R.menu.hack, menu);
        menu.setHeaderTitle("Hack");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                startActivity(new Intent(Register.this,PrayerTimeActivity.class));
                finish();
                break;
        }
        return super.onContextItemSelected(item);
    }
}