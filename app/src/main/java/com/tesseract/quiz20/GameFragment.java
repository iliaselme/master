package com.tesseract.quiz20;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tesseract.quiz20.DbHelper.DbHelper;
import com.tesseract.quiz20.Model.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*https://www.youtube.com/watch?v=31_-aCj_Evg
Deze tutorial gebruikt om de logica van de quiz de begrijpen en ook voor de doneActivity en CustomAdapter.

*/
public class GameFragment extends Fragment implements View.OnClickListener {
    Activity context;
    final static long INTERVAL = 1000; //1sec
    final static long TIMEOUT = 7000;
    int progressValue = 0;

    CountDownTimer mCountDown; //for progressbar
    List<Question> questionPlay = new ArrayList<>();
    DbHelper db;
    int index = 0, score = 0, thisQuestion = 0, totalQuestion = 30, correctAnswer;

    //control
    ProgressBar progressBar;
    Button b_answer1, b_answer2, b_answer3, b_answer4;
    TextView l_score, l_question;
    ImageView iv_flag;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        context = getActivity();
        if (context != null && isAdded()) {


            db = new DbHelper(context);
            try {
                db.createDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }

            iv_flag = (ImageView) view.findViewById(R.id.iv_flag);
            b_answer1 = (Button) view.findViewById(R.id.b_answer1);
            b_answer2 = (Button) view.findViewById(R.id.b_answer2);
            b_answer3 = (Button) view.findViewById(R.id.b_answer3);
            b_answer4 = (Button) view.findViewById(R.id.b_answer4);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
            l_score = (TextView) view.findViewById(R.id.l_score);
            l_question = (TextView) view.findViewById(R.id.l_question);

            b_answer1.setOnClickListener(this);
            b_answer2.setOnClickListener(this);
            b_answer3.setOnClickListener(this);
            b_answer4.setOnClickListener(this);

            questionPlay = db.getAllQuestion();

            mCountDown = new CountDownTimer(TIMEOUT, INTERVAL) {
                @Override
                public void onTick(long l) {
                    progressBar.setProgress(progressValue);
                    progressValue++;
                }

                @Override
                public void onFinish() {
                    mCountDown.cancel();
                    showQuestion(++index);
                }
            };
            showQuestion(index);


            return view;

        }else return null;
    }

    private void showQuestion(int index) {
        if (index < totalQuestion) {
            thisQuestion++;
            l_question.setText(String.format("%d/%d", thisQuestion, totalQuestion));
            progressBar.setProgress(progressValue);
            progressValue = 0;

            int imageId = this.getResources().getIdentifier(questionPlay.get(index).getImage().toLowerCase(), "drawable", getContext().getPackageName());
            iv_flag.setBackgroundResource(imageId);
            String a1 = questionPlay.get(index).getAnswerA();
            if (a1.contains("-")) {
                a1 = a1.replaceAll("-", "_");
            }
            String a2 = questionPlay.get(index).getAnswerB();
            if (a2.contains("-")) {
                a2 = a2.replaceAll("-", "_");
            }
            String a3 = questionPlay.get(index).getAnswerC();
            if (a3.contains("-")) {
                a3 = a3.replaceAll("-", "_");
            }
            String a4 = questionPlay.get(index).getAnswerD();
            if (a4.contains("-")) {
                a4 = a1.replaceAll("-", "_");
            }
            b_answer1.setText(a1);
            b_answer2.setText(a2);
            b_answer3.setText(a3);
            b_answer4.setText(a4);

            mCountDown.start();

        } else {
            Intent intent = new Intent(getContext(), DoneActivity.class);
            Bundle dataSend = new Bundle();
            dataSend.putInt("SCORE", score);
            dataSend.putInt("TOTAL", totalQuestion);
            dataSend.putInt("CORRECT", correctAnswer);
            intent.putExtras(dataSend);
            startActivity(intent);
            if (isAdded()) {
                return;
            }
        }
    }

    @Override
    public void onClick(View v) {
        mCountDown.cancel();
        if (index < totalQuestion) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().toString().equals(questionPlay.get(index).getCorrectAnswer())) {
                score += 10;
                correctAnswer++;
                showQuestion(++index);
            } else
                showQuestion(++index);
            l_score.setText(String.format("%d", score));
        }

}




}
