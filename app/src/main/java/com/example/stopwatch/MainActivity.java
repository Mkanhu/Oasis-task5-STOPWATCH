package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
  private TextView timer;
  private Button Start , Hold , Reset;
  int seconds;
  boolean isRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer=findViewById(R.id.timerText);
        Start=findViewById(R.id.button);
        Hold=findViewById(R.id.button2);
        Reset=findViewById(R.id.button3);

        startTimer();

        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               isRunning=true;
            }
        });
        Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              isRunning=false;
            }
        });
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 isRunning=false;
                 seconds=0;
            }
        });

    }
    public void startTimer() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int s=seconds%60;
                int m=seconds/60;
                int h=m/60;
                if(isRunning==true)
                        seconds++;
                String formatString = String.format(Locale.getDefault(),"%02d:%02d:%02d",h,m,s);
                timer.setText(formatString);
                handler.postDelayed(this,1000);
            }
        };
        handler.post(runnable);
    }
}