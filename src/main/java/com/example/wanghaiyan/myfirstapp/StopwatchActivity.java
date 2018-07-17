package com.example.wanghaiyan.myfirstapp;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StopwatchActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tx_showtime;
    private Button btn_start,btn_stop,btn_reset;
    private int seconds = 0;
    private boolean running=false;
    private boolean wasrunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasrunning = savedInstanceState.getBoolean("wasrunning");
        }
        init();
    }

    private void init(){
        tx_showtime = findViewById(R.id.tx_showtime);
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_reset = findViewById(R.id.btn_reset);
        btn_start.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_reset.setOnClickListener(this);
        runTimer();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_start:
                running = true;

                break;
            case R.id.btn_stop:
                running = false;
                break;
            case R.id.btn_reset:
                running = false;
                seconds = 0;
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
    }

    private void runTimer(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                tx_showtime.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        wasrunning = running;
        running = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(wasrunning){
            running = true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasrunning = running;
        running = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(wasrunning){
            running = true;
        }
    }
}
