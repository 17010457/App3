package com.example.derrick.app3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class HomeFragment extends Fragment {

    CardView cvMen, cvWomen, cvKids;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        final View v = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager viewPager = v.findViewById(R.id.viewPager);
        ImageAdapter adapter = new ImageAdapter(getActivity());
        viewPager.setAdapter(adapter);

        cvMen = v.findViewById(R.id.cvMen);
        cvWomen = v.findViewById(R.id.cvWomen);
        cvKids = v.findViewById(R.id.cvKids);

        cvMen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CatalogActivity.class);
                intent.putExtra("id","10");
                startActivity(intent);
            }
        });

        cvWomen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CatalogActivity.class);
                intent.putExtra("id","20");
                startActivity(intent);
            }
        });

        cvKids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CatalogActivity.class);
                intent.putExtra("id","30");
                startActivity(intent);
            }
        });



        return v;
    }

}
