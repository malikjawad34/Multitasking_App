package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Spinner spnCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        spnCountries = findViewById(R.id.spnCountries);
        ArrayAdapter<String> spnAdpCountries = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,getResources().getStringArray(R.array.strCountries));
        spnCountries.setAdapter(spnAdpCountries);
        ListView listView = (ListView) findViewById(R.id.viewList);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.cars));
        listView.setAdapter(adapter);
    }
}