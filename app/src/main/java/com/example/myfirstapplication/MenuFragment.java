package com.example.myfirstapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;


public class MenuFragment extends ListFragment {
    String[] Countries = new String[]
            {"Pakistan", "India", "Iran", "China", "Afghanistan", "Russia", "Australia"
            ,"Maldives","Bangladesh","USA","Saudi Arabia","Yemen","Morocco","Turkey"
            ,"Malaysia","Indonesia","Qatar","Kuwait","Bahrain","Mauritania"};

    String[] Capitals = new String[]{};
    String[] PresidentName = new String[]{};
    String[] Area = new String[]{};
    String[] Population = new String[]{};
    String[] Language = new String[]{};
    Integer[] Flags = new Integer[]{};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Countries);
        setListAdapter(adapter);
        Capitals = getActivity().getResources().getStringArray(R.array.capital);
        PresidentName = getActivity().getResources().getStringArray(R.array.presidentName);
        Area = getActivity().getResources().getStringArray(R.array.area);
        Population = getActivity().getResources().getStringArray(R.array.population);
        Language = getActivity().getResources().getStringArray(R.array.nationalLanguage);
        Flags = new Integer[]
                {R.mipmap.pakistan_flag,R.mipmap.india,R.mipmap.iran,R.mipmap.china,
                 R.mipmap.afg,R.mipmap.russia,R.mipmap.aus,R.mipmap.maldivies,
                 R.mipmap.bagn,R.mipmap.usa,R.mipmap.saudi_arabia,R.mipmap.yemen,
                 R.mipmap.morocco,R.mipmap.turkey,R.mipmap.malaysia,R.mipmap.indonesia,
                 R.mipmap.qatar,R.mipmap.kuwait,R.mipmap.bahrain,R.mipmap.mauritania};
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextFragment txt = (TextFragment) getParentFragmentManager().findFragmentById(R.id.fragment2);

        txt.change(Countries[position], Capitals[position], PresidentName[position], Area[position], Population[position], Language[position], Flags[position]);
        getListView().setSelector(android.R.color.holo_orange_dark);
    }
}
