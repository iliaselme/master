package com.tesseract.quiz20.Common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tesseract.quiz20.Model.Ranking;
import com.tesseract.quiz20.R;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends BaseAdapter{

    private Context context;
    private List<Ranking> lstRanking;

    public CustomAdapter(Context context, List<Ranking> lstRanking) {
        this.context = context;
        this.lstRanking = lstRanking;
    }

    @Override
    public int getCount() {
        return Math.min(lstRanking.size(),3);
    }

    @Override
    public Object getItem(int i) {
        return lstRanking.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row,null);

        ImageView imgTop = (ImageView)view.findViewById(R.id.imgTop);
        TextView txtTop = (TextView)view.findViewById(R.id.txtTop);
        TextView txtUsername = (TextView)view.findViewById(R.id.txtUsername);

        if(position==0)
            imgTop.setImageResource(R.drawable.top1);
        else if(position==1)
            imgTop.setImageResource(R.drawable.top2);
        else
            imgTop.setImageResource(R.drawable.top3);

        txtTop.setText(String.valueOf(lstRanking.get(position).getScore()));
        txtUsername.setText(String.valueOf(lstRanking.get(position).getUsername()));

        return view;
    }
}
