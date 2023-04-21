package com.example.liveforcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        GifImageView gifImageView = (GifImageView) findViewById(R.id.splashgif);
        gifImageView.setGifImageResource(R.drawable.bg1);
        TextView txt = findViewById(R.id.splashtxt);

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