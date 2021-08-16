package com.example.mahtab.downloadingimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button downloadButton;

    public void downloadImage(View view){
        Imagedownloader task = new Imagedownloader();
        Bitmap myImage ;
       try {
           myImage = task.execute("https://i.picsum.photos/id/866/200/300.jpg").get();
           imageView.setImageBitmap(myImage);
       }
       catch (Exception e){
           e.printStackTrace();

       }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        downloadButton = findViewById(R.id.downloadButton);

    }

    public class Imagedownloader extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... urls) {

            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }



        }
    }
}
