package com.tesseract.quiz20;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class OptionsActivity extends AppCompatActivity {

    Button btnLangToEng,btnLangToNl,btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        btnLangToEng = (Button)findViewById(R.id.btnLangToEng);
        btnLangToNl = (Button)findViewById(R.id.btnLangToNl);
        btnHome = (Button)findViewById(R.id.btnHome);

        btnLangToEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("EN");
                returnHome();
            }
        });

        btnLangToNl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLocale("NL");
                returnHome();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               returnHome();
            }
        });

    }

    public void setLocale(String lang) {

        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, OptionsActivity.class);
        startActivity(refresh);
    }

    public void returnHome(){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
