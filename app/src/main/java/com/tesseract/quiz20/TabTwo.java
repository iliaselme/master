package com.tesseract.quiz20;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.support.v4.app.Fragment;

import com.tesseract.quiz20.Common.CustomAdapter;
import com.tesseract.quiz20.DbHelper.DbHelper;
import com.tesseract.quiz20.Model.Ranking;

import java.util.List;

public class TabTwo extends Fragment {
    ListView lstView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_two,container,false);


        lstView = (ListView)view.findViewById(R.id.lstRanking);
        DbHelper db = new DbHelper(getContext());
        List<Ranking> lstRanking = db.getRanking();
        if(lstRanking.size()>0)
        {
            CustomAdapter adapter = new CustomAdapter(getContext(),lstRanking);
            lstView.setAdapter(adapter);
        }



        return view;
    }



}
