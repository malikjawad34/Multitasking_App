package com.example.myfirstapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class CarsList extends AppCompatActivity {

    Button btn;
    ListView simpleList;
    String carNameList[] = {"TOYOTA TUNDRA","TOYOTA COROLLA","KIA SPORTAGE","MG 6"};
    String carPrizeList[] = {"142000 RS","192000 RS","245000 RS","362000 RS"};
    int cars[] = {R.drawable.tundra,R.drawable.corolla,R.drawable.kia_sportage,R.drawable.mg_t};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_cars);
        simpleList = (ListView) findViewById(R.id.simpleListView);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), carNameList, carPrizeList, cars);
        simpleList.setAdapter(customAdapter);

        btn = findViewById(R.id.btnNotify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNotification();
            }
        });
    }

    private void addNotification() {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("YOUR_CHANNEL_ID",
                    "YOUR_CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("YOUR_NOTIFICATION_CHANNEL_DESCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.corolla);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "YOUR_CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_lancher1) // notification icon
                .setContentTitle("Rate Our App") // title for notification
                .setContentText("please give rating to our app")// message for notification
                .setAutoCancel(true)// clear notification after click
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bm));
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}
