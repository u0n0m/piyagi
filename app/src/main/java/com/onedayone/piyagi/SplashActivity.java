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
    private static final int[] IMAGE_RESOURCES = { R.drawable.tittle_0000,
            R.drawable.tittle_0001, R.drawable.tittle_0002, R.drawable.tittle_0003,
            R.drawable.tittle_0004, R.drawable.tittle_0005, R.drawable.tittle_0006,
            R.drawable.tittle_0007, R.drawable.tittle_0008, R.drawable.tittle_0009,
            R.drawable.tittle_0010, R.drawable.tittle_0011, R.drawable.tittle_0012,
            R.drawable.tittle_0013, R.drawable.tittle_0014, R.drawable.tittle_0015,
            R.drawable.tittle_0016, R.drawable.tittle_0017, R.drawable.tittle_0018,
            R.drawable.tittle_0019, R.drawable.tittle_0020, R.drawable.tittle_0021,
            R.drawable.tittle_0022, R.drawable.tittle_0023, R.drawable.tittle_0024,
            R.drawable.tittle_0025, R.drawable.tittle_0026, R.drawable.tittle_0027,
            R.drawable.tittle_0028, R.drawable.tittle_0029, R.drawable.tittle_0030,
            R.drawable.tittle_0031, R.drawable.tittle_0032, R.drawable.tittle_0033,
            R.drawable.tittle_0034, R.drawable.tittle_0035, R.drawable.tittle_0036,
            R.drawable.tittle_0037, R.drawable.tittle_0038, R.drawable.tittle_0039,
            R.drawable.tittle_0040, R.drawable.tittle_0041, R.drawable.tittle_0042,
            R.drawable.tittle_0043, R.drawable.tittle_0044, R.drawable.tittle_0045,
            R.drawable.tittle_0046, R.drawable.tittle_0047, R.drawable.tittle_0048,
            R.drawable.tittle_0049, R.drawable.tittle_0050, R.drawable.tittle_0051,
            R.drawable.tittle_0052, R.drawable.tittle_0053, R.drawable.tittle_0054,
            R.drawable.tittle_0055, R.drawable.tittle_0056, R.drawable.tittle_0057,
            R.drawable.tittle_0058, R.drawable.tittle_0059, R.drawable.tittle_0060,
            R.drawable.tittle_0061, R.drawable.tittle_0062, R.drawable.tittle_0063,
            R.drawable.tittle_0064, R.drawable.tittle_0065, R.drawable.tittle_0066,
            R.drawable.tittle_0067, R.drawable.tittle_0068, R.drawable.tittle_0069
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