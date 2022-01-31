package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class PrayerTimeActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    TextView tvFajr, tvdhuhr, tvasr, tvmaghrib, tvisha, tvLocation, tvDate, tvSunRise;
    Button searchbtn, tvClean;
    EditText editsearch;
    SeekBar seekBar;
    AlertDialog.Builder alertDialog;
/*
    String userName;
    private FirebaseAuth auth;
    private FirebaseFirestore FFStore;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_time);

        mQueue = Volley.newRequestQueue(this);

        seekBar = findViewById(R.id.seekBar);
        alertDialog = new AlertDialog.Builder(this);

//        auth = FirebaseAuth.getInstance();
//        FFStore = FirebaseFirestore.getInstance();
//
//        DocumentReference documentReference = FFStore.collection("Users").document(auth.getCurrentUser().getUid());
//        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        userName = document.getString("name");
//                        getSupportActionBar().setTitle("Welcome " + userName);
//                    } else {
//                        Toast.makeText(PrayerTimeActivity.this, "No such Document", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(PrayerTimeActivity.this, "Error" + task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        editsearch = findViewById(R.id.searchEdit);
        tvLocation = findViewById(R.id.locationTv);
        tvDate = findViewById(R.id.dateTv);
        tvFajr = findViewById(R.id.fajrTv);
        tvSunRise = findViewById(R.id.sunRiseTv);
        tvdhuhr = findViewById(R.id.dhuhrTv);
        tvasr = findViewById(R.id.asrTv);
        tvmaghrib = findViewById((R.id.maghribTv));
        tvisha = findViewById(R.id.ishaTv);

        searchbtn = findViewById(R.id.btnOnClick);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mlocation = editsearch.getText().toString().trim();

                if(mlocation.isEmpty()){
                    Toast.makeText(PrayerTimeActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                }
                else{
                    jsonParse(mlocation);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvFajr.setTextSize(i);
                tvSunRise.setTextSize(i);
                tvdhuhr.setTextSize(i);
                tvasr.setTextSize(i);
                tvmaghrib.setTextSize(i);
                tvisha.setTextSize(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    protected void onStart(){
        super.onStart();

        tvClean = findViewById(R.id.cleanData);
        tvClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.setMessage("Are you sure you want to Clean your store information!").setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sh.edit();
                                myEdit.clear();
                                myEdit.commit();

                                String search = sh.getString("name", "");
                                String location = sh.getString("location", "Set Location");
                                String date = sh.getString("date", "Set Date");
                                String fajr = sh.getString("fajrTime", "Set Time");
                                String sunRise = sh.getString("sunRiseTime", "Set Time");
                                String dhuhr = sh.getString("dhuhrTime", "Set Time");
                                String asr = sh.getString("asrTime", "Set Time");
                                String maghrib = sh.getString("maghribTime", "Set Time");
                                String isha = sh.getString("ishaTime", "Set Time");

                                tvLocation.setText(location);
                                tvDate.setText(date);
                                tvFajr.setText(fajr);
                                tvSunRise.setText(sunRise);
                                tvdhuhr.setText(dhuhr);
                                tvasr.setText(asr);
                                tvmaghrib.setText(maghrib);
                                tvisha.setText(isha);
                                editsearch.setText(search);
                            }
                        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                alertDialog.create();
                alertDialog.setTitle("Clean Data");
                alertDialog.show();
            }
        });

    }

    private void jsonParse(String location) {

        String url ="https://api.aladhan.com/v1/timingsByCity?city="+location+"&country=Pakistan&method=1&school=1";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                JSONObject data = response.getJSONObject("data");

                                JSONObject date = data.getJSONObject("date");
                                tvDate.setText(date.getString("readable"));

                                JSONObject item = data.getJSONObject("timings");
                                String Fajr = item.getString("Fajr");
                                String SunRise = item.getString("Sunrise");
                                String Dhuhr = item.getString("Dhuhr");
                                String Asr = item.getString("Asr");
                                String Maghrib = item.getString("Maghrib");
                                String Isha = item.getString("Isha");

                                tvFajr.setText(Fajr);
                                tvSunRise.setText(SunRise);
                                tvdhuhr.setText(Dhuhr);
                                tvasr.setText(Asr);
                                tvmaghrib.setText(Maghrib);
                                tvisha.setText(Isha);
                                tvLocation.setText(location + ", Pakistan");

                        } catch (JSONException e) {
                            Toast.makeText(PrayerTimeActivity.this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(PrayerTimeActivity.this, "error " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String search = sh.getString("name", "");
        String location = sh.getString("location", "Set Location");
        String date = sh.getString("date", "Set Date");
        String fajr = sh.getString("fajrTime", "Set Time");
        String sunRise = sh.getString("sunRiseTime", "Set Time");
        String dhuhr = sh.getString("dhuhrTime", "Set Time");
        String asr = sh.getString("asrTime", "Set Time");
        String maghrib = sh.getString("maghribTime", "Set Time");
        String isha = sh.getString("ishaTime", "Set Time");

        tvLocation.setText(location);
        tvDate.setText(date);
        tvFajr.setText(fajr);
        tvSunRise.setText(sunRise);
        tvdhuhr.setText(dhuhr);
        tvasr.setText(asr);
        tvmaghrib.setText(maghrib);
        tvisha.setText(isha);
        editsearch.setText(search);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("name", editsearch.getText().toString());
        myEdit.putString("location", tvLocation.getText().toString());
        myEdit.putString("date", tvDate.getText().toString());
        myEdit.putString("fajrTime", tvFajr.getText().toString());
        myEdit.putString("sunRiseTime", tvSunRise.getText().toString());
        myEdit.putString("dhuhrTime", tvdhuhr.getText().toString());
        myEdit.putString("asrTime", tvasr.getText().toString());
        myEdit.putString("maghribTime", tvmaghrib.getText().toString());
        myEdit.putString("ishaTime", tvisha.getText().toString());
        myEdit.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mainMenu = getMenuInflater();
        mainMenu.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.item1:
                intent = new Intent(PrayerTimeActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.item2:
                intent = new Intent(PrayerTimeActivity.this,FragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.item3:
                intent = new Intent(PrayerTimeActivity.this,CarsList.class);
                startActivity(intent);
                break;
            case R.id.item4:
//                auth.signOut();
                intent = new Intent(PrayerTimeActivity.this,Login.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}