package com.example.liveforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import pl.droidsonroids.gif.GifImageView;

public class SplashScreen extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        TextView txt = findViewById(R.id.splashtxt);
        GifImageView gifImageView = findViewById(R.id.splashGif);

        Animation animImg = AnimationUtils.loadAnimation(this, R.anim.splashanimimg);
        gifImageView.startAnimation(animImg);
        Animation animtxt = AnimationUtils.loadAnimation(this, R.anim.splashanimtxt);
        txt.startAnimation(animtxt);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, HomeScreen.class);
                startActivity(i);
                finish();
            }
        }, 3000);
    }
}