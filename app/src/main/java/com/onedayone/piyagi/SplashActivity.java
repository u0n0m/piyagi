package com.onedayone.piyagi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.onedayone.piyagi.R;

public class SplashActivity extends Activity{
    private AnimationDrawable frameAnimation;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        view = (ImageView) findViewById(R.id.splash_animation_imageview);
        view.setBackgroundResource(R.drawable.splash_animation_images.splash_animation1);
        frameAnimation = (AnimationDrawable) view.getBackground();
        frameAnimation.start();

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.splash_animation2);
                frameAnimation = (AnimationDrawable) view.getBackground();
                frameAnimation.start();
            }
        }, 1500);
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.splash_animation3);
                frameAnimation = (AnimationDrawable) view.getBackground();
                frameAnimation.start();
            }
        }, 3000);
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.splash_animation4);
                frameAnimation = (AnimationDrawable) view.getBackground();
                frameAnimation.start();
            }
        }, 4500);
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.splash_animation5);
                frameAnimation = (AnimationDrawable) view.getBackground();
                frameAnimation.start();
            }
        }, 6000);
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.splash_animation6);
                frameAnimation = (AnimationDrawable) view.getBackground();
                frameAnimation.start();
            }
        }, 7500);
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackgroundResource(R.drawable.splash_animation7);
                frameAnimation = (AnimationDrawable) view.getBackground();
                frameAnimation.start();
            }
        }, 9000);

        //Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();       // 3 초후 이미지를 닫아버림
            }
        }, 11000);
    }
/*
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            frameAnimation.start();
        } else {
            frameAnimation.stop();
        }
    }*/

}