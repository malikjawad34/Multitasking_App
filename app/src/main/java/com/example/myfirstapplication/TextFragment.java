package com.example.myfirstapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TextFragment extends Fragment {
    TextView textCountry, textCapital, textPresidentName, textLanguage, textArea, textPopulation;
    ImageView imageView;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.text_fragment, container, false);
        textCountry = (TextView) view.findViewById(R.id.Country);
        textCapital = (TextView) view.findViewById(R.id.Capital);
        textPresidentName = (TextView) view.findViewById(R.id.PresidentName);
        textLanguage = (TextView) view.findViewById(R.id.Language);
        textArea = (TextView) view.findViewById(R.id.Area);
        textPopulation = (TextView) view.findViewById(R.id.Population);
        imageView = (ImageView) view.findViewById(R.id.flag);

        return view;
    }

    public void change(String txtCountry, String txtCapital, String txtPresidentName, String txtArea, String txtPopulation, String txtLanguage, Integer intFlag) {
        textCountry.setText(txtCountry);
        textCapital.setText(txtCapital);
        textPresidentName.setText(txtPresidentName);
        textLanguage.setText(txtLanguage);
        textArea.setText(txtArea);
        textPopulation.setText(txtPopulation);

        imageView.setImageResource(intFlag);
    }
}
