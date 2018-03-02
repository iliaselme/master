package com.tesseract.quiz20;


import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tesseract.quiz20.DbHelper.DbHelper;
import com.tesseract.quiz20.Model.Ranking;

import java.util.List;

public class DoneActivity extends AppCompatActivity {

    Button b_retry;
    TextView l_totalScore,l_totalQuestion;
    Button btnAddHighScore;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

       final DbHelper db = new DbHelper(this);

    b_retry = findViewById(R.id.b_retry);
    l_totalScore =findViewById(R.id.l_totalScore);
    l_totalQuestion = findViewById(R.id.l_totalQuestion);
    btnAddHighScore = findViewById(R.id.btnAddHighScore);
    etName = findViewById(R.id.etName);


    String scoreString = getResources().getString(R.string.Score);
    String passedString = getResources().getString(R.string.Passed);
    final String errorUsername = getResources().getString(R.string.ToastErrorUsername);

    Bundle extra = getIntent().getExtras();
    if(extra!=null){
        final int score = extra.getInt("SCORE");
        int totalQuestion = extra.getInt("TOTAL");
        int correctAnswer = extra.getInt("CORRECT");

        l_totalScore.setText(scoreString + score);
        l_totalQuestion.setText(passedString+ correctAnswer + "/" + totalQuestion);



        final List<Ranking> lstRanking = db.getRanking();
        if(lstRanking==null){
            btnAddHighScore.setVisibility(View.VISIBLE);
            etName.setVisibility(View.VISIBLE);
            btnAddHighScore.requestFocus();
            btnAddHighScore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!etName.getText().toString().isEmpty()){
                        String username = etName.getText().toString();
                        db.insertScore(score,username);
                        btnAddHighScore.setVisibility(view.GONE);
                        etName.setVisibility(view.GONE);

                    }else


                    Toast.makeText(getApplicationContext(),errorUsername, Toast.LENGTH_SHORT).show();
                }
            });

        }
        else{
            if(score>lstRanking.get(2).getScore()){
                btnAddHighScore.setVisibility(View.VISIBLE);
                etName.setVisibility(View.VISIBLE);
                btnAddHighScore.requestFocus();
                btnAddHighScore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!etName.getText().toString().isEmpty()){
                            String username = etName.getText().toString();
                            db.insertScore(score,username);
                            btnAddHighScore.setVisibility(view.GONE);
                            etName.setVisibility(view.GONE);
                        }else
                        Toast.makeText(getApplicationContext(),errorUsername, Toast.LENGTH_SHORT).show();
                    }
                });



            }
        }


        b_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //als gebruiker geen naam ingeeft maar een high score is dan wordt als naam guest gezet
                if(score>lstRanking.get(2).getScore()){
                    if(!etName.getText().toString().isEmpty()){
                        startActivity(intent);
                        finish();
                    }else{
                        db.insertScore(score,"Guest");
                        startActivity(intent);
                        finish();
                    }
                }
                startActivity(intent);
                finish();


            }
        });

    }}


}
