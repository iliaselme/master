package com.tesseract.quiz20;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ImageRestActivity extends AppCompatActivity {


    ImageView imageSearched;
    public static TextView nameCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_rest);

        Intent intent = getIntent();
        String country = intent.getStringExtra("country").toLowerCase();

        FetchData process = new FetchData(country);
        process.execute();

        nameCountry = (TextView) findViewById(R.id.nameCountry);
        country = country.replaceAll(" ", "_");
        imageSearched = (ImageView)findViewById(R.id.imageSearched);
        Resources res =getResources();
        try {
            int resID = getResources().getIdentifier(country,"drawable",getPackageName());
            Drawable drawable = res.getDrawable(resID);
            imageSearched.setImageDrawable(drawable);
        }catch(Exception e){
            Toast.makeText(ImageRestActivity.this,"Niet gevonden",Toast.LENGTH_SHORT).show();
        }

    }
}
