package com.example.myfirstapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class PickTime extends AppCompatActivity {
    TimePicker picker;
    TextView tvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_time);

        tvw = findViewById(R.id.pickedTime);
        picker = findViewById(R.id.time_picker);
        tvw.setText("SET TIME");

        picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                if(i == 12)
                    tvw.setText(i + " : " + i1 + " PM");
                else if(i > 12)
                    tvw.setText((i-12) +" : " + i1 + " PM");
                else
                    tvw.setText(i + " : " + i1 + " AM");
            }
        });
    }
}