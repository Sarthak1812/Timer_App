package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button start;
    TextView hrs, min, sec;
    MediaPlayer m1 = new MediaPlayer();
    long HoursInMilliseconds , MinuteInMilliseconds , SecondInMilliseconds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hrs = findViewById(R.id.hrs);
        min = findViewById(R.id.min);
        sec = findViewById(R.id.sec);

        m1 = MediaPlayer.create(this, R.raw.sound);

        final TextView mnumber = findViewById(R.id.mnumber);
        final TextView done = findViewById(R.id.done);

        //Checking the input from user

        if (hrs.getText().toString().isEmpty() && min.getText().toString().isEmpty() && sec.getText().toString().isEmpty()) {
            hrs.setError("No entry");
            min.setError("No entry");
            sec.setError("No entry");
        } else {}



        start = findViewById(R.id.startbtn);

        start.setEnabled(true);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start.setEnabled(false);
                if (hrs.getText().toString().isEmpty() && min.getText().toString().isEmpty() && !sec.getText().toString().isEmpty()) {
                    SecondInMilliseconds = Integer.parseInt(sec.getText().toString()) * 1000;
                    hrs.setText("0");
                    min.setText("0");
                } else if (hrs.getText().toString().isEmpty() && !min.getText().toString().isEmpty() && sec.getText().toString().isEmpty()) {
                    MinuteInMilliseconds = Integer.parseInt(min.getText().toString()) * 60 * 1000;
                    hrs.setText("0");
                    sec.setText("0");
                } else if (!hrs.getText().toString().isEmpty() && min.getText().toString().isEmpty() && sec.getText().toString().isEmpty()) {
                    HoursInMilliseconds = Integer.parseInt(hrs.getText().toString()) * 60 * 60 * 1000;
                    sec.setText("0");
                    min.setText("0");
                } else if (hrs.getText().toString().isEmpty() && !min.getText().toString().isEmpty() && !sec.getText().toString().isEmpty()) {
                    SecondInMilliseconds = Integer.parseInt(sec.getText().toString()) * 1000;
                    MinuteInMilliseconds = Integer.parseInt(min.getText().toString()) * 60 * 1000;
                    hrs.setText("0");
                } else if (!hrs.getText().toString().isEmpty() && min.getText().toString().isEmpty() && !sec.getText().toString().isEmpty()) {
                    SecondInMilliseconds = Integer.parseInt(sec.getText().toString()) * 1000;
                    HoursInMilliseconds = Integer.parseInt(hrs.getText().toString()) * 60 * 60 * 1000;
                    min.setText("0");
                } else if (!hrs.getText().toString().isEmpty() && !min.getText().toString().isEmpty() && sec.getText().toString().isEmpty()) {
                    MinuteInMilliseconds = Integer.parseInt(min.getText().toString()) * 60 * 1000;
                    HoursInMilliseconds = Integer.parseInt(hrs.getText().toString()) * 60 * 60 * 1000;
                    sec.setText("0");
                } else if (!hrs.getText().toString().isEmpty() && !min.getText().toString().isEmpty() && !sec.getText().toString().isEmpty()) {
                    MinuteInMilliseconds = Integer.parseInt(min.getText().toString()) * 60 * 1000;
                    HoursInMilliseconds = Integer.parseInt(hrs.getText().toString()) * 60 * 60 * 1000;
                    SecondInMilliseconds = Integer.parseInt(sec.getText().toString()) * 1000;
                }
                new CountDownTimer(HoursInMilliseconds + MinuteInMilliseconds + SecondInMilliseconds, 1000) {
                    public void onTick(long millisecondsUntilDone) {
                        mnumber.setText("Time Left :  " + String.valueOf(millisecondsUntilDone / 1000));
                    }

                    public void onFinish() {
                        done.setText("TIME's UP...!!!");
                        m1.start();
                        start.setEnabled(true);
                    }
                }.start();
                HoursInMilliseconds = 0;
                MinuteInMilliseconds = 0;
                SecondInMilliseconds = 0;
                done.setText(" ");
            }
        });

        //TODO : Clearing the values ....... after a run
        hrs.clearFocus();
        min.clearFocus();
        sec.clearFocus();

    }
}

