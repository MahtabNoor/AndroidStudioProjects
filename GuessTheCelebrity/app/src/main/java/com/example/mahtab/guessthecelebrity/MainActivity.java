package com.example.mahtab.guessthecelebrity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    Button option1Button;
    Button option2Button;
    Button option3Button;
    Button option4Button;
    ImageView celebrityImageView;
    Pattern p;
    Matcher m;
    String name1 = "";
    String imageUrl = "";
    String celebrity;
    String celebrityJohnnyDepp;
    String celebrityArnoldSchwarzenegger;
    String celebrityEmmaWatson;
    String celebrityDanielRadcliffe;
    String celebrityLeonardoDiCaprio;
    int option1;
    int option2;
    int option3;
    int option4;
    int[] disc;


    public void guess(View view){
       String tappedClick = view.getTag().toString();
       String buttonChoosed ;
       if (tappedClick.equals("1")){
           buttonChoosed = "Button 1";
           setImage(imageAddress(celebrity));
       }
       else if (tappedClick.equals("2")){
           buttonChoosed = "Button 2";
       }
       else if (tappedClick.equals("0")){
           buttonChoosed = "the Image";
       }
       else if (tappedClick.equals("3")){
           buttonChoosed = "Button 3";
       }
       else {
           buttonChoosed = "Button 4";
       }
        Log.i("button selected","You have selected "+buttonChoosed);

    }

   public void setImage(String imageAddress) {
        ImageDownloader task = new ImageDownloader();
        Bitmap myImage;

        try {
            myImage = task.execute(imageAddress).get();
            celebrityImageView.setImageBitmap(myImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    public String celebrityName(String name){



    }

    public String imageAddress(String name){
        p = Pattern.compile("src=\"(.*?)\"");
        m = p.matcher(name);


        while (m.find()){
            imageUrl +=  m.group(1);
        }

        return imageUrl;
    }


    int rightOption()
    {
        disc = new int[]{option1, option2, option3, option4};
        return disc[new Random().nextInt(disc.length)];
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);
        celebrityImageView = findViewById(R.id.celebrityImageView);


        celebrityJohnnyDepp = "<img alt=\"Johnny Depp\"height=\"209\"src=\"https://m.media-amazon.com/images/M/MV5BMTM0ODU5Nzk2OV5BMl5BanBnXkFtZTcwMzI2ODgyNQ@@._V1_UY209_CR3,0,140,209_AL_.jpg\" ";

        celebrityArnoldSchwarzenegger = "<img alt=\"Arnold Schwarzenegger\"height=\"209\"src=\"https://m.media-amazon.com/images/M/MV5BMTI3MDc4NzUyMV5BMl5BanBnXkFtZTcwMTQyMTc5MQ@@._V1_UY209_CR13,0,140,209_AL_.jpg\"";

        celebrityEmmaWatson = "<img alt=\"Emma Watson\"height=\"209\"src=\"https://m.media-amazon.com/images/M/MV5BMTQ3ODE2NTMxMV5BMl5BanBnXkFtZTgwOTIzOTQzMjE@._V1_UY209_CR14,0,140,209_AL_.jpg\"";

        celebrityDanielRadcliffe = "<img alt=\"Daniel Radcliffe\"height=\"209\"src=\"https://m.media-amazon.com/images/M/MV5BMTg4NTExODc3Nl5BMl5BanBnXkFtZTgwODUyMDEzMDE@._V1_UY209_CR8,0,140,209_AL_.jpg\"";

        celebrityLeonardoDiCaprio = "<img alt=\"Leonardo DiCaprio\"height=\"209\"src=\"https://m.media-amazon.com/images/M/MV5BMjI0MTg3MzI0M15BMl5BanBnXkFtZTcwMzQyODU2Mw@@._V1_UY209_CR7,0,140,209_AL_.jpg\"";


        option1 = new Random().nextInt(5);
        option2 = new Random().nextInt(5);
        option3 = new Random().nextInt(5);
        option4 = new Random().nextInt(5);

        while(option2 == option1 ){
            option2 = new Random().nextInt(5);
        }
        while(option3 == option1 || option3 == option2 ){
            option3 = new Random().nextInt(5);
        }
        while(option4 == option1 || option4 == option2 || option4 == option3){
            option4 = new Random().nextInt(5);
        }


        ArrayList<String> celebrityNameArray = new ArrayList<String>();
        ArrayList<String> celebrityImageAddressArray = new ArrayList<String>();
        String[] celebritiesList = new String[] {celebrityJohnnyDepp,celebrityArnoldSchwarzenegger,celebrityEmmaWatson,celebrityDanielRadcliffe,celebrityLeonardoDiCaprio};
        int rand = new Random().nextInt(5) ;
        System.out.println("Elements of given array: ");
        //Loop through the array by incrementing value of i
        for (int i = 0; i < celebritiesList.length; i++) {
            celebrityNameArray.add(celebrityName(celebritiesList[i]));
            celebrityImageAddressArray.add(imageAddress(celebritiesList[i]));
        }


        option1Button.setText(celebrityName(celebrityJohnnyDepp));
        option2Button.setText(celebrityNameArray.get(option2));
        option3Button.setText(celebrityNameArray.get(option3));
        option4Button.setText(celebrityNameArray.get(option4));

        setImage(celebrityImageAddressArray.get(rand));


    }

    class ImageDownloader extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection ;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream in = urlConnection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;


            } catch (Exception e) {
                e.printStackTrace();


                return null;
            }
        }
    }
}
