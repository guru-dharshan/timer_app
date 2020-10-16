package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
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

import com.example.stopwatch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int mminc=0, ssinc=0,set;

    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.progressBarmin.setProgress(0);
        binding.progressBar.setProgress(0);
        mediaPlayer=MediaPlayer.create(this,R.raw.over);


        binding.startclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time= (mminc*60)+ssinc;
                countDownTimer=new CountDownTimer(time*1000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        change(millisUntilFinished/1000);
                    }

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onFinish() {
                        binding.tmin.setText("00:00");
                        binding.mintt.setText("00");
                        binding.sectt.setText("00");
                        binding.progressBar.setProgress(0);
                        binding.progressBarmin.setProgress(0);
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
                binding.tmin.setText("0"+ minit +" :0"+ set);
            }
            else if(minit<10){
                binding.tmin.setText("0" + minit +" : "+ set);
            }
            else if(set<10){
                binding.tmin.setText(minit +" :0"+ set);
            }
        }
        else {
            binding.tmin.setText(minit + " : " + set);
        }
        binding.progressBarmin.setProgress(minit);
        binding.progressBar.setProgress((int) sect);
    }

    public void mininc(View view){

        mminc++;
        if(mminc<10) {
            String ouput = getResources().getString(R.string.zero)+mminc;
            binding.mintt.setText(ouput);
        }
        else{
            binding.mintt.setText(String.valueOf(mminc));
        }
        binding.progressBarmin.setProgress(mminc);
        binding.progressBar.setProgress(mminc*60+ssinc);
    }

    public void mindec(View view){
        if(mminc>0) {
            mminc--;
            if (mminc < 10) {
                String output =getResources().getString(R.string.zero)+mminc;
                binding.mintt.setText(output);
            } else {
                binding.mintt.setText(String.valueOf(mminc));
            }
        }
        else{
            binding.mintt.setText(("00"));
        }
        binding.progressBarmin.setProgress(mminc);
        binding.progressBar.setProgress(mminc*60+ssinc);
    }

    public void secinc(View view){
        ssinc++;
        if(ssinc<10) {
            String output = getResources().getString(R.string.zero)+ssinc;
            binding.sectt.setText(output);
        }
        else{
            binding.sectt.setText(String.valueOf(ssinc));
        }
        if(mminc==0){
            binding.progressBar.setProgress(ssinc);
        }
        else {
            binding.progressBar.setProgress(mminc * 60 + ssinc);
        }
    }

    public void secdec(View view){
        if(ssinc>0) {
            ssinc--;
            if (ssinc < 10) {
                String output = getResources().getString(R.string.zero)+ssinc;
                binding.sectt.setText(output);
            } else {
                binding.sectt.setText(String.valueOf(ssinc));
            }
            if(mminc==0){
                binding.progressBar.setProgress(ssinc);
            }
            else {
                binding.progressBar.setProgress(mminc * 60 + ssinc);
            }
        }
        else{
            binding.sectt.setText(getResources().getString(R.string._00));
        }

    }

}