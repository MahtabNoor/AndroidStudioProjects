package com.example.mahtab.connect3tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.VISIBLE;


public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 0 :player1 , 1:player2, 2:Empty
    int gameState[] = {2,2,2,2,2,2,2,2,2};

    int winningPositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean activeGame = true;

  public void userChoice(View view) {

      ImageView click = (ImageView) view;
      int tappedClick = Integer.parseInt(click.getTag().toString());


      if (gameState[tappedClick] == 2 && activeGame == true)
      {
          click.setTranslationX(-2000);
          Log.i("tag", click.getTag().toString());
          gameState[tappedClick] = activePlayer;
          if (activePlayer == 0) {

              click.setImageResource(R.drawable.player1);
              activePlayer = 1;
          }
          else {

              click.setImageResource(R.drawable.player2);
              activePlayer = 0;
          }

          click.animate().translationXBy(2000).rotation(3600).setDuration(500);
          for (int winningPosition[] : winningPositions)
          {
              if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
              {
                 String winner = "" ;
                  if (activePlayer == 1)
                  {
                      winner = "player 1";
                 }
                 else
                     {
                      winner = "player 2";
                  }
                  Toast.makeText(this, winner +" has won!", Toast.LENGTH_SHORT).show();
                  activeGame = false;
                  Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                  TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                  winnerTextView.setText(winner +" has won!");
                  playAgainButton.setVisibility(View.VISIBLE);
                  winnerTextView.setVisibility(View.VISIBLE);
              }
              else {
                  int empty = 0;
                  for (int i=0;i<gameState.length;i++){

                      if (gameState[i]==2){
                          empty++;

                      }
                  }
                  if (empty == 0){
                      Toast.makeText(this,  "Draw", Toast.LENGTH_SHORT).show();
                      activeGame = false;
                      Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                      TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                      winnerTextView.setText("Draw!");
                      playAgainButton.setVisibility(View.VISIBLE);
                      winnerTextView.setVisibility(View.VISIBLE);

                  }
              }
          }

      }

      }

      public void playAgain(View view) {
          Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
          TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
          playAgainButton.setVisibility(View.INVISIBLE);
          winnerTextView.setVisibility(View.INVISIBLE);

          GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

          for(int i=0; i< gridLayout.getChildCount(); i++) {
              ImageView click = (ImageView) gridLayout.getChildAt(i);
              // do stuff with child view
              click.setImageDrawable(null);

          }


          for (int i=0;i<gameState.length;i++)
          {
              gameState[i]=2;
          }
           activePlayer = 0;
          // 0 :player1 , 1:player2, 2:Empty



           activeGame = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
