package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_Click, btnExit;
    TextView contextBar, rateMessage;
    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = findViewById(R.id.ratingBar);
        rateMessage = findViewById(R.id.rateBarMessage);
        contextBar = findViewById(R.id.context_bar);
        registerForContextMenu(contextBar);
        btnExit = findViewById(R.id.exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater contextMenu = getMenuInflater();
        contextMenu.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select an action");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.item1:
                intent = new Intent(MainActivity.this,PickDate.class);
                startActivity(intent);
                break;
            case R.id.item2:
                intent = new Intent(MainActivity.this,PickTime.class);
                startActivity(intent);
                break;
            case R.id.item3:
                intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
            case R.id.item4:
                intent = new Intent(MainActivity.this,ClockActivity.class);
                startActivity(intent);
                break;

        }
        return super.onContextItemSelected(item);
    }

    public void onStart()
    {
        super.onStart();

        btn_Click = findViewById(R.id.btnClick);
        btn_Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this,btn_Click);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        Snackbar snackbar;
                        switch (menuItem.getItemId()){
                            case R.id.item1:
                                Snackbar.make( btn_Click,"Capital of Pakistan is Islamabad", Snackbar.LENGTH_SHORT).show();
                                break;
                            case R.id.item2:
                                snackbar = Snackbar.make( btn_Click,"Capital of China is Beijing", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                                break;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                switch ((int) v){
                    case  1:
                        rateMessage.setText("Disappointed");
                        break;
                    case  2:
                        rateMessage.setText("Not Good");
                        break;
                    case  3:
                        rateMessage.setText("Good");
                        break;
                    case  4:
                        rateMessage.setText("Very Good");
                        break;
                    case  5:
                        rateMessage.setText("Impressive");
                        break;
                }
            }
        });
    }
}