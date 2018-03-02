package com.tesseract.quiz20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TabOne extends Fragment{


    Button btnStartMusic,btnStopMusic;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_one, container, false);
        final Button btnPlay = (Button) view.findViewById(R.id.bPlay);
        final Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnStartMusic = (Button) view.findViewById(R.id.btnStartMusic);
        btnStopMusic = (Button) view.findViewById(R.id.btnStopMusic);

        btnStartMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"start",Toast.LENGTH_SHORT).show();
                getActivity().startService(new Intent(getActivity(),MyService.class));
            }
        });


        btnStopMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"stop",Toast.LENGTH_SHORT).show();
                getActivity().stopService(new Intent(getActivity(),MyService.class));
            }
        });

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameFragment gameFragment = new GameFragment();
                FragmentManager manager = getFragmentManager();
                manager.beginTransaction().replace(R.id.constraintLayout, gameFragment, gameFragment.getTag()).commit();
                btnPlay.setVisibility(View.GONE);
                btnSearch.setVisibility(View.GONE);

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;


    }

}