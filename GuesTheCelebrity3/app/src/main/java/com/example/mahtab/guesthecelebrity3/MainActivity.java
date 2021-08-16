package com.example.mahtab.guesthecelebrity3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Pattern p;
    Matcher m;
    ArrayList<String> celebImages = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int choosenCeleb;
    String[] answers = new String[4];
    int locationOfCorrectAnswer ;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    String tappedClick;


    ImageView imageView;

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection = null;
            String result = "";

            try {
                url = new URL(urls[0]);
                Log.i("URL",urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data !=-1){
                    char current = (char) data;
                    result+= current;
                    data = reader.read();

                }
                return result;


            } catch (Exception e) {
                e.printStackTrace();
                return "Failed1" ;
            }
        }
    }

    public class ImageDownloader extends AsyncTask<String,Void,Bitmap>{
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
    public void chosenCeleb(View view){
        tappedClick = view.getTag().toString();
        if (tappedClick.equals(Integer.toString(locationOfCorrectAnswer))){
            Toast.makeText(getApplicationContext(), "Correct Answer", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong Answer.Correct Answer is "+celebNames.get(choosenCeleb), Toast.LENGTH_LONG).show();

        }
        newQuestion();
    }
    public void  newQuestion(){
        Random rand = new Random();
        choosenCeleb = rand.nextInt(celebImages.size());

        ImageDownloader imageTask = new ImageDownloader();
        Bitmap choosenCelebImage = null;
        try {
            choosenCelebImage = imageTask.execute(celebImages.get(choosenCeleb)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(choosenCelebImage);

        int randomNamePosition  ;
        locationOfCorrectAnswer = rand.nextInt(4);
        for (int i=0;i<4;i++){
            randomNamePosition = rand.nextInt(celebNames.size());
            if (i == locationOfCorrectAnswer){
                answers[i] = celebNames.get(choosenCeleb);
            }
            else {
                while (randomNamePosition == choosenCeleb){
                    randomNamePosition = rand.nextInt(celebNames.size());}
                answers[i] = celebNames.get(randomNamePosition);
            }

        }


        button0.setText(answers[0]);
        button1.setText(answers[1]);
        button2.setText(answers[2]);
        button3.setText(answers[3]);




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

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("https://www.imdb.com/list/ls052283250/").get();
            String[] splitResult = result.split("<script type=\"text/javascript\">");

            p = Pattern.compile("<img alt=\"(.*?)\"");
            m = p.matcher(splitResult[11]);

            while (m.find()){
                celebNames.add(m.group(1));
            }

            p = Pattern.compile("src=\"(.*?)\"");
            m = p.matcher(splitResult[11]);

            while (m.find()){
                celebImages.add(m.group(1));
            }

          newQuestion();

        } catch (Exception e) {
           e.printStackTrace();
        }


    }
}
