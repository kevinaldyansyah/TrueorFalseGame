package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity {
    private final String SCORE_KEY="SCORE";
    private final String INDEX_KEY="INDEX";
    private TextView mTxtQuestion;
    private Button btnF;
    private Button btnT;
    private int mQuestionIndex;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizstatsTextView;
    private int mUserScore;
    TextView txtHello;
    private String nama;
    private String KEY_NAME = "NAMA";

    private QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1,true),
            new QuizModel(R.string.q2,false),
            new QuizModel(R.string.q3,true),
            new QuizModel(R.string.q4,true),
            new QuizModel(R.string.q5,true),
            new QuizModel(R.string.q6,true),
            new QuizModel(R.string.q7,true),
            new QuizModel(R.string.q8,false),
            new QuizModel(R.string.q9,true),
            new QuizModel(R.string.q10,false),
    };
    final int USER_PROGRESS=(int)Math.ceil(100.0/questionCollection.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        mTxtQuestion= findViewById(R.id.txtQuestion);
        QuizModel q1=questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar = findViewById(R.id.quizPB);
        mQuizstatsTextView = findViewById(R.id.txtQuizStats);

        txtHello = findViewById(R.id.txtHello);
        Bundle extras = getIntent().getExtras();
        nama = extras.getString(KEY_NAME);
        txtHello.setText(" " + nama);

        btnT = findViewById(R.id.btnTrue);
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();
            }
        });

        btnF=findViewById(R.id.btnFalse);
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();
            }
        });
    }

    private void changeQuestionOnButtonClick(){
        mQuestionIndex=(mQuestionIndex+1)%10;
        if(mQuestionIndex==0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is Finished");
            quizAlert.setMessage("Your Score is "+mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            quizAlert.show();
        }
        mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mQuizstatsTextView.setText(mUserScore+""+"/ 10");
    }

    private void evaluateUserAnswer(boolean userGuess){
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer();
        if(currentQuestionAnswer== userGuess){
          FancyToast.makeText(getApplicationContext(),"Jawaban Benar!", FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,true).show();
          mUserScore=mUserScore+1;
        }
        else{
            FancyToast.makeText(getApplicationContext(),"Jawaban Salah!",FancyToast.LENGTH_SHORT,FancyToast.CONFUSING,true).show();
        }
    }
}