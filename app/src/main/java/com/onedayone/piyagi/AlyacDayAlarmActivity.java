package com.onedayone.piyagi;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AlyacDayAlarmActivity extends AppCompatActivity {
    private AnimationDrawable frameAnimation;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyac_day_alarm);

        ImageButton test = (ImageButton) findViewById(R.id.alyac_day_alarm_btn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                System.gc();
                finish();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.gc();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            view = (ImageView) findViewById(R.id.alyac_day_animation_imageview);
            view.setBackgroundResource(R.drawable.alyac_day_animation_item1);
            frameAnimation = (AnimationDrawable) view.getBackground();
            frameAnimation.start();

/*            Handler hd = new Handler();
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frameAnimation.stop();
                    System.gc();
                    view.setBackgroundResource(R.drawable.alyac_day_animation_item2);
                    frameAnimation = (AnimationDrawable) view.getBackground();
                    frameAnimation.start();
                }
            }, 1400);
            hd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    frameAnimation.stop();
                    System.gc();
                }
            }, 7900);*/
        } else {
            frameAnimation.stop();
            System.gc();
            finish();
        }
    }
}
