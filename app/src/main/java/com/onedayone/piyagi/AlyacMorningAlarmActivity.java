package com.onedayone.piyagi;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AlyacMorningAlarmActivity extends AppCompatActivity {
    private AnimationDrawable frameAnimation;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyac_morning_alarm);

        ImageButton test = (ImageButton) findViewById(R.id.alyac_morning_alarm_btn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
//                Handler hd = new Handler();
//                hd.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        finish();       // 3 초후 이미지를 닫아버림
//                    }
//                }, 8500);
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            view = (ImageView) findViewById(R.id.alyac_morning_animation_imageview);
            view.setBackgroundResource(R.drawable.alyac_morning_animation_item1);
            frameAnimation = (AnimationDrawable) view.getBackground();
            frameAnimation.start();

            Handler hd = new Handler();
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundResource(R.drawable.alyac_morning_animation_item2);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 1400);
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundResource(R.drawable.alyac_morning_animation_item3);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 2800);
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundResource(R.drawable.alyac_morning_animation_item4);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 4200);
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundResource(R.drawable.alyac_morning_animation_item5);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 4600);
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundResource(R.drawable.alyac_morning_animation_item6);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 6000);
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackgroundResource(R.drawable.alyac_morning_animation_item7);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 7900);
        } else {
            frameAnimation.stop();
            finish();
        }
    }
}