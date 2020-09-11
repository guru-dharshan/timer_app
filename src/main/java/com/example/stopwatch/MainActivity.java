package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int prog=0;
    private int progsec=0;
    int mminc=0;
    int ssinc=0;
    ProgressBar progressBarmin;
    ProgressBar progressBar;
    CardView start;
    CountDownTimer countDownTimer;
    TextView tmin;
    TextView emin;
    TextView esec;
    ImageView minc;
    ImageView mdec;
    ImageView sinc;
    ImageView sdec;
    MediaPlayer mediaPlayer;
    //long time=0;
    int set;
   
   public void mininc(View view){
       mminc++;
       if(mminc<10) {
           emin.setText("0"+String.valueOf(mminc));
       }
       else{
           emin.setText(String.valueOf(mminc));
       }
       progressBarmin.setProgress(mminc);
       progressBar.setProgress(mminc*60+ssinc);
   }
    public void mindec(View view){
        if(mminc>0) {
            mminc--;
            if (mminc < 10) {
                emin.setText("0" + String.valueOf(mminc));
            } else {
                emin.setText(String.valueOf(mminc));
            }
        }
        else{
            emin.setText(String.valueOf("00"));
        }
        progressBarmin.setProgress(mminc);
        progressBar.setProgress(mminc*60+ssinc);
    }
    public void secinc(View view){
        ssinc++;
        if(ssinc<10) {
            esec.setText("0"+String.valueOf(ssinc));
        }
        else{
            esec.setText(String.valueOf(ssinc));
        }
        if(mminc==0){
            progressBar.setProgress(ssinc);
        }
        else {
            progressBar.setProgress(mminc * 60 + ssinc);
        }
    }
    public void secdec(View view){
        if(ssinc>0) {
            ssinc--;
            if (ssinc < 10) {
                esec.setText("0" + String.valueOf(ssinc));
            } else {
                esec.setText(String.valueOf(ssinc));
            }
            if(mminc==0){
                progressBar.setProgress(ssinc);
            }
            else {
                progressBar.setProgress(mminc * 60 + ssinc);
            }
        }
        else{
            esec.setText(String.valueOf("00"));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar);
        progressBarmin=findViewById(R.id.progressBarmin);
        start=findViewById(R.id.startclock);
        tmin=findViewById(R.id.tmin);
        emin=findViewById(R.id.mintt);
        esec=findViewById(R.id.sectt);
        progressBarmin.setProgress(0);
        progressBar.setProgress(0);
        mediaPlayer=MediaPlayer.create(this,R.raw.over);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time= (mminc*60)+ssinc;
                //Toast.makeText(MainActivity.this, "Started"+String.valueOf(time), Toast.LENGTH_SHORT).show();
                countDownTimer=new CountDownTimer(time*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        change(millisUntilFinished/1000);
                    }

                    @Override
                    public void onFinish() {
                        tmin.setText("00:00");
                        emin.setText("00");
                        esec.setText("00");
                        progressBar.setProgress(0);
                        progressBarmin.setProgress(0);
                        mminc=0;
                        ssinc=0;

                    }
                }.start();
            }
        });

    }
    public void change(long timer){
        int minit= (int) (timer/60);
        long sect = (int) (timer-(minit*60));
        set=(int) (timer-(minit*60));
        //minit=0;
        sect=timer;
        if(sect%60==0 && minit!=0){
            minit--;
        }
        sect--;
        if(set==0){
            set=60;
        }
        set--;
        if(sect==0){
            mediaPlayer.start();
        }
        if(set<10 || minit<10){
            if (minit<10 && set<10) {
                tmin.setText("0"+String.valueOf(minit)+" :0"+String.valueOf(set));
            }
            else if(minit<10){
                tmin.setText("0"+String.valueOf(minit)+" : "+String.valueOf(set));
            }
            else if(set<10){
                tmin.setText(String.valueOf(minit)+" :0"+String.valueOf(set));
            }
        }
        else {
            tmin.setText(String.valueOf(minit) + " : " + String.valueOf(set));
        }
        progressBarmin.setProgress(minit);
        progressBar.setProgress((int) sect);
    }
}