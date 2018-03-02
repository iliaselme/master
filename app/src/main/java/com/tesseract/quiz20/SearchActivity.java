package com.tesseract.quiz20;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
//https://stackoverflow.com/questions/5254100/how-to-set-an-imageviews-image-from-a-string

//https://stackoverflow.com/questions/13394054/android-restrict-user-to-select-from-autocompletion-textview-only/15648326

public class SearchActivity extends AppCompatActivity {

    String var;
    AutoCompleteTextView autoCompleteTextView;
    String[] countries;
    Button btnSearchCountry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.country);
        countries = getResources().getStringArray(R.array.countries_array);
        btnSearchCountry = (Button)findViewById(R.id.btnSearchCountry);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                var = adapter.getItem(i).toString();
            }
        });


        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                var = null;
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });




        btnSearchCountry.setOnClickListener(new View.OnClickListener() {
            String myString = getResources().getString(R.string.ToastErrorInvalidValue);
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ImageRestActivity.class);
                if(var!=null) {
                    intent.putExtra("country", autoCompleteTextView.getText().toString());
                    startActivity(intent);
                }else
                  Toast.makeText(SearchActivity.this, myString, Toast.LENGTH_SHORT).show();
            }
        });

    }

}
