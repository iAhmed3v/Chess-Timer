package com.ahmed.chesstimer.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ahmed.chesstimer.R;

public class OneMinActivity extends AppCompatActivity {

    Button playerOneButton, playerTwoButton, pauseButton, restartButton;
    TextView playerOneTextCounter, playerTwoTextCounter;
    CountDownTimer timerOne, timerTwo;
    int counterOne = 60 , counterTwo = 60;

   private  AlertDialog.Builder builder;
   private AlertDialog dialog;
   private LayoutInflater inflater;
   private MediaPlayer playerOneButtonSound;
   private MediaPlayer playerTwoButtonSound;
   private MediaPlayer clockFinished;
   private Resources resources;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clock_activity);

        playerOneButton = findViewById(R.id.playerOneButton);
        playerTwoButton = findViewById(R.id.playerTwoButton);
        pauseButton = findViewById(R.id.pauseButton);
        restartButton = findViewById(R.id.restartButton);

        playerOneTextCounter = findViewById(R.id.playerOneTextCounter);
        playerTwoTextCounter = findViewById(R.id.playerTwoTextCounter);

        playerOneButtonSound = MediaPlayer.create(getApplicationContext(), R.raw.chess_clock_switch1);
        playerTwoButtonSound = MediaPlayer.create(getApplicationContext(), R.raw.chess_clock_switch2);
        clockFinished = MediaPlayer.create(getApplicationContext(), R.raw.chess_clock_time_ended);

        resources = getResources();

        restartButton.setEnabled(false);
        pauseButton.setEnabled(false);

        playerOneTextCounter.setText(R.string.start_the_timer);
        playerOneButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
        playerOneTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));

        playerTwoTextCounter.setText(R.string.start_the_timer);
        playerTwoButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
        playerTwoTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));



      playerTwoButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              playerTwoButton.setEnabled(false);
              restartButton.setEnabled(true);
              pauseButton.setEnabled(true);
              reversTimerTwo(counterTwo, playerOneTextCounter);
              playerTwoButtonSound.start();

          }
      });

      playerOneButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              playerOneButton.setEnabled(false);
              restartButton.setEnabled(true);
              pauseButton.setEnabled(true);
              reversTimerOne(counterOne, playerTwoTextCounter);
              playerOneButtonSound.start();
          }
      });

      pauseButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {


              updateUIState(v);

              if(playerTwoButton.isEnabled()){

                  timerOne.cancel();

              }else if(playerOneButton.isEnabled()){

                  timerTwo.cancel();
              }
          }
      });

      restartButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              updateUIState(v);

              if(playerTwoButton.isEnabled()){

                  timerOne.cancel();

              }else if(playerOneButton.isEnabled()){

                  timerTwo.cancel();
              }

              builder = new AlertDialog.Builder(OneMinActivity.this);

              inflater = LayoutInflater.from(OneMinActivity.this);
              View view = inflater.inflate(R.layout.reset_dialog, null);

              Button noButton = view.findViewById(R.id.noButton);
              Button yesButton = view.findViewById(R.id.yesButton);

              builder.setView(view);
              dialog = builder.create();
              dialog.show();

              yesButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      Intent intent = new Intent(OneMinActivity.this, MainActivity.class);
                      startActivity(intent);
                  }
              });

              noButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {

                      dialog.dismiss();

                  }
              });
          }
      });

    }

    private void reversTimerOne(int Seconds , final TextView playerTwoTextCounter) {


        playerTwoButton.setEnabled(true);

        timerOne = new CountDownTimer(Seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if(playerOneButton.isEnabled()) {

                    playerTwoButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
                    playerTwoTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));

                    cancel();

                } else {

                    int seconds = (int) (millisUntilFinished / 1000);
                    counterOne = seconds;
                    int minutes = seconds / 60;
                    seconds = seconds % 60;


                    playerTwoButton.setBackgroundDrawable(resources.getDrawable(R.drawable.custom_button));
                    playerTwoTextCounter.setTextColor(Color.WHITE);

                    playerTwoTextCounter.setText(String.format("%02d" , minutes) + ":" + String.format("%02d" , seconds));

                }
            }

            @Override
            public void onFinish() {

                playerTwoButton.setBackgroundDrawable(resources.getDrawable(R.drawable.finish_button_background));

                playerTwoButton.setText(R.string.stop_the_timer);

                clockFinished.start();

                playerTwoButton.setEnabled(false);

            }
        };
        timerOne.start();
    }

    private void reversTimerTwo(int Seconds , final TextView playerOneTextCounter) {

        playerOneButton.setEnabled(true);

        timerTwo = new CountDownTimer(Seconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                if(playerTwoButton.isEnabled()) {

                    playerOneButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
                    playerOneTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));

                    cancel();

                } else {

                    int seconds = (int) (millisUntilFinished / 1000);
                    counterTwo = seconds;
                    int minutes = seconds / 60;
                    seconds = seconds % 60;


                    playerOneButton.setBackgroundDrawable(resources.getDrawable(R.drawable.custom_button));
                    playerOneTextCounter.setTextColor(Color.WHITE);

                    playerOneTextCounter.setText(String.format("%02d" , minutes) + ":" + String.format("%02d" , seconds));
                }
            }

            @Override
            public void onFinish() {

                playerOneButton.setBackgroundDrawable(resources.getDrawable(R.drawable.finish_button_background));

                playerTwoButton.setText(R.string.stop_the_timer);

                clockFinished.start();

                playerOneButton.setEnabled(false);


            }
        };
          timerTwo.start();
    }

    private void updateUIState(View v){

        switch(v.getId()){

            case R.id.pauseButton:
                playerTwoButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
                playerTwoTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));

                playerOneButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
                playerOneTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));
                break;

            case R.id.restartButton:
                playerTwoButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
                playerTwoTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));

                playerOneButton.setBackgroundDrawable(resources.getDrawable(R.drawable.paused_button_background));
                playerOneTextCounter.setTextColor(resources.getColor(R.color.paused_background_text_timer));
                break;

        }


    }

}