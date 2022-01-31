package com.example.myfirstapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class PickDate extends AppCompatActivity {
    DatePicker datePicker;
    Button btnGet;
    TextView tvw,tvw2;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date);

        datePicker = findViewById(R.id.date_picker);
        tvw = findViewById(R.id.pickedDate);
        tvw.setText(datePicker.getDayOfMonth()+"/"+ (datePicker.getMonth() + 1)+"/"+datePicker.getYear());
        datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                tvw.setText(i2 + "/" + (i1+1) + "/" + i);
            }
        });

        tvw2 = findViewById(R.id.pickDate);
        btnGet = findViewById(R.id.getDate);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvw2.setText(datePicker.getDayOfMonth()+"/"+ (datePicker.getMonth() + 1)+"/"+datePicker.getYear());
            }
        });
    }
}