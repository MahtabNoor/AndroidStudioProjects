package com.example.mahtab.guessthecelebrity4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String result;
    Pattern p;
    Matcher m;
    ArrayList<String> celebrityName = new ArrayList<>();
    ArrayList<String> celebrityImage = new ArrayList<>();
    int chosenCelebrity;
    ImageView imageView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    String[] answers = new String[4];

    int correctAnswerPosition;
    TextView timerTextView;
    TextView questionTextView;
    TextView answerTextView;
    int totalNoOfQuestion = 0;
    int totalNoOfCorrectAnswer = 0;


    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url = null;
            try {
                url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                Reader reader = new InputStreamReader(inputStream);
                int data = reader.read();
                String result = "";

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();

                }
                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }

    public void newQuestion() {


        Random rand = new Random();
        chosenCelebrity = rand.nextInt(celebrityName.size());
        correctAnswerPosition = rand.nextInt(4);


        DownloadImage imageTask = new DownloadImage();
        Bitmap chosenCelebImage = null;
        try {
            chosenCelebImage = imageTask.execute(celebrityImage.get(chosenCelebrity)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(chosenCelebImage);
        int randomCelebrity;

        for (int i = 0; i < 4; i++) {
            randomCelebrity = new Random().nextInt(celebrityName.size());
            while ( randomCelebrity == chosenCelebrity) {
                randomCelebrity = new Random().nextInt(celebrityName.size());
            }
            if (i == correctAnswerPosition) {
                answers[i] = celebrityName.get(chosenCelebrity);
            } else {
                if (i ==1){
                    while(celebrityName.get(randomCelebrity) == answers[0]){
                        randomCelebrity = new Random().nextInt(celebrityName.size());
                    }}
                if (i ==2){
                    while(celebrityName.get(randomCelebrity) == answers[0]|| celebrityName.get(randomCelebrity) == answers[1]){
                        randomCelebrity = new Random().nextInt(celebrityName.size());
                    }}
                if (i ==3){
                    while(celebrityName.get(randomCelebrity) == answers[0]|| celebrityName.get(randomCelebrity) == answers[1] ||  celebrityName.get(randomCelebrity) == answers[2]){
                        randomCelebrity = new Random().nextInt(celebrityName.size());
                    }}

                answers[i] = celebrityName.get(randomCelebrity);


            }}



        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);


    }

    public void buttonClicked(View view) {
        int tappedClick = Integer.parseInt(view.getTag().toString());
        if (tappedClick == correctAnswerPosition) {
            answerTextView.setText("Correct Answer");
            totalNoOfCorrectAnswer+=1;


        } else {
            answerTextView.setText("Wrong Answer");

        }
        totalNoOfQuestion +=1;
        questionTextView.setText(""+totalNoOfCorrectAnswer +"/" + totalNoOfQuestion);
        newQuestion();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        answerTextView = (TextView) findViewById(R.id.answerTextView);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);


        DownloadTask task = new DownloadTask();
        try {
            result = task.execute("https://www.imdb.com/list/ls052283250/").get();


            String[] resultSplit = result.split(" <h1 class=\"header list-name\">100 MOST POPULAR CELEBRITIES IN THE WORLD</h1>");

            p = Pattern.compile("<img alt=\"(.*?)\"");
            m = p.matcher(resultSplit[1]);
            while (m.find()) {

                celebrityName.add(m.group(1));

            }

            p = Pattern.compile("src=\"(.*?)\"");
            m = p.matcher(resultSplit[1]);
            while (m.find()) {

                celebrityImage.add(m.group(1));
            }

            newQuestion();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
/* for(int i = 0; i<4; i++) {
            randomCelebrity = new Random().nextInt(celebrityName.size());
            while ( randomCelebrity == chosenCelebrity) {
                randomCelebrity = new Random().nextInt(celebrityName.size());
            }
            if (i == correctAnswerPosition) {
                answers.add(chosenCelebrity);
            } else {
                if (i ==1){
                    while(randomCelebrity == answers.get(0)){
                        randomCelebrity = new Random().nextInt(celebrityName.size());
                    }}
                if (i ==2){
                    while(randomCelebrity == answers.get(0)|| randomCelebrity == answers.get(1)){
                        randomCelebrity = new Random().nextInt(celebrityName.size());
                    }}
                if (i ==3){
                    while(randomCelebrity == answers.get(0)|| randomCelebrity == answers.get(1) ||  randomCelebrity == answers.get(2)){
                        randomCelebrity = new Random().nextInt(celebrityName.size());
                    }}

                answers.add(randomCelebrity);
*/

/*  for (int i = 0; i < 4; i++) {
            randomCelebrity = new Random().nextInt(celebrityName.size());

            if (i == correctAnswerPosition) {
                answers[i] = celebrityName.get(chosenCelebrity);
            } else {
                while (randomCelebrity == chosenCelebrity) {
                    randomCelebrity = new Random().nextInt(celebrityName.size());
                }
                answers[i] = celebrityName.get(randomCelebrity);

                }

            }
*/


