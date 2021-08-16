package com.example.mahtab.brainteaser2;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score;
    int numberOfQuestions;
    TextView scoreTextView;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView calculationTextView;
    TextView timerTextView;
    Button playAgainButton;
    GridLayout gridLayout;

    public void newQuestion(){

        answers.clear();
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b= rand.nextInt(21);

        calculationTextView.setText(Integer.toString(a)+ " + " +Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        for(int i = 0;i<4;i++){
            if (i == locationOfCorrectAnswer ){
                answers.add(a+b);
            }
            else{
                int wronganswer = rand.nextInt(41);
                while (wronganswer == a+b){
                    wronganswer = rand.nextInt(41);}
                answers.add(wronganswer);

            }
        }


        textView1.setText(String.format("%d",answers.get(0)));
        textView2.setText(String.format("%d",answers.get(1)));
        textView3.setText(String.format("%d",answers.get(2)));
        textView4.setText(String.format("%d",answers.get(3)));


    }

    public void chooseAnswer(View view){

        String selectedAnswer =  view.getTag().toString();
        if (Integer.toString(locationOfCorrectAnswer).equals(selectedAnswer)) {
            resultTextView.setText("Correct! :)");
            score++;
        }
        else{
            resultTextView.setText("Wrong! :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions)
        );

        newQuestion();

    }

    public void playAgain (View view){

        textView1.setEnabled(true);
        textView2.setEnabled(true);
        textView3.setEnabled(true);
        textView4.setEnabled(true);

        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
                textView1.setEnabled(false);
                textView2.setEnabled(false);
                textView3.setEnabled(false);
                textView4.setEnabled(false);


            }
        }.start();

        newQuestion();



    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 =  findViewById(R.id.textView1);
        textView2 =  findViewById(R.id.textView2);
        textView3 =  findViewById(R.id.textView3);
        textView4 =  findViewById(R.id.textView4);
        calculationTextView = (TextView) findViewById(R.id.calculationTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gridLayout = findViewById(R.id.gridLayout);



        playAgainButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.playAgainButton));




    }
}
