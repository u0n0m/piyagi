package com.onedayone.piyagi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.onedayone.piyagi.R;

public class SplashActivity extends Activity{
    FasterAnimationsContainer mFasterAnimationsContainer;
    private static final int[] IMAGE_RESOURCES = { R.drawable.intro_0000,
            R.drawable.intro_0001, R.drawable.intro_0002, R.drawable.intro_0003,
            R.drawable.intro_0004, R.drawable.intro_0005, R.drawable.intro_0006,
            R.drawable.intro_0007, R.drawable.intro_0008, R.drawable.intro_0009,
            R.drawable.intro_0010, R.drawable.intro_0011, R.drawable.intro_0012,
            R.drawable.intro_0013, R.drawable.intro_0014, R.drawable.intro_0015,
            R.drawable.intro_0016, R.drawable.intro_0017, R.drawable.intro_0018,
            R.drawable.intro_0019, R.drawable.intro_0020, R.drawable.intro_0021,
            R.drawable.intro_0022, R.drawable.intro_0023, R.drawable.intro_0024,
            R.drawable.intro_0025, R.drawable.intro_0026, R.drawable.intro_0027,
            R.drawable.intro_0028, R.drawable.intro_0029, R.drawable.intro_0030,
            R.drawable.intro_0031, R.drawable.intro_0032, R.drawable.intro_0033,
            R.drawable.intro_0034, R.drawable.intro_0035, R.drawable.intro_0036,
            R.drawable.intro_0037, R.drawable.intro_0038, R.drawable.intro_0039,
            R.drawable.intro_0040, R.drawable.intro_0041, R.drawable.intro_0042,
            R.drawable.intro_0043, R.drawable.intro_0044, R.drawable.intro_0045,
            R.drawable.intro_0046, R.drawable.intro_0047, R.drawable.intro_0048,
            R.drawable.intro_0049, R.drawable.intro_0050, R.drawable.intro_0051,
            R.drawable.intro_0052, R.drawable.intro_0053, R.drawable.intro_0054,
            R.drawable.intro_0055, R.drawable.intro_0056, R.drawable.intro_0057,
            R.drawable.intro_0058, R.drawable.intro_0059, R.drawable.intro_0060,
            R.drawable.intro_0061, R.drawable.intro_0062, R.drawable.intro_0063,
            R.drawable.intro_0064, R.drawable.intro_0065, R.drawable.intro_0066,
            R.drawable.intro_0067, R.drawable.intro_0068, R.drawable.intro_0069
    };

    private static final int ANIMATION_INTERVAL = 130;  // 100ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                //mFasterAnimationsContainer = null;
                System.gc();
                finish();       // ?? 초후 닫아버림
            }
        }, 10000);
    }

    @Override
    public void onPause() {
        mFasterAnimationsContainer.stop();
        System.gc();
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        mFasterAnimationsContainer.stop();
        System.gc();
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        mFasterAnimationsContainer.stop();
        System.gc();
        super.onDestroy();
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){

        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            ImageView imageView = (ImageView) findViewById(R.id.splash_animation_imageview);
            mFasterAnimationsContainer = FasterAnimationsContainer
                    .getInstance(imageView);
            mFasterAnimationsContainer.addAllFrames(IMAGE_RESOURCES,
                    ANIMATION_INTERVAL);
            mFasterAnimationsContainer.start();

        } else {
            mFasterAnimationsContainer.stop();
            System.gc();
            finish();
        }
    }

}