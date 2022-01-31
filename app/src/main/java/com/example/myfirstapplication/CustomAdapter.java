package com.example.myfirstapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String carNameList[];
    String carPrizeList[];
    int cars[];
    LayoutInflater inflater;

    public CustomAdapter(Context applicationContext, String[] carNameList, String[] carPrizeList, int[] cars) {
        this.context = context;
        this.carNameList = carNameList;
        this.carPrizeList = carPrizeList;
        this.cars = cars;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return carNameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_listview, null);
        TextView carName = (TextView) view.findViewById(R.id.title);
        TextView carPrize= (TextView) view.findViewById(R.id.subtitle);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        carPrize.setText(carPrizeList[i]);
        carName.setText(carNameList[i]);
        icon.setImageResource(cars[i]);
        return view;
    }
}
