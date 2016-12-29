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

public class AlyacNightAlarmActivity extends AppCompatActivity {
    FasterAnimationsContainer mFasterAnimationsContainer;
    private static final int[] IMAGE_RESOURCES = { R.drawable.inter_0000,
            R.drawable.inter_0001, R.drawable.inter_0002, R.drawable.inter_0003,
            R.drawable.inter_0004, R.drawable.inter_0005, R.drawable.inter_0006,
            R.drawable.inter_0007, R.drawable.inter_0008, R.drawable.inter_0009,
            R.drawable.inter_0010, R.drawable.inter_0011, R.drawable.inter_0012,
            R.drawable.inter_0013, R.drawable.inter_0014, R.drawable.inter_0015,
            R.drawable.inter_0016, R.drawable.inter_0017, R.drawable.inter_0018,
            R.drawable.inter_0019, R.drawable.inter_0020, R.drawable.inter_0021,
            R.drawable.inter_0022, R.drawable.inter_0023, R.drawable.inter_0024,
            R.drawable.inter_0025, R.drawable.inter_0026, R.drawable.inter_0027,
            R.drawable.inter_0028, R.drawable.inter_0029, R.drawable.inter_0030,
            R.drawable.inter_0031, R.drawable.inter_0032, R.drawable.inter_0033,
            R.drawable.inter_0034, R.drawable.inter_0035, R.drawable.inter_0036,
            R.drawable.inter_0037, R.drawable.inter_0038, R.drawable.inter_0039,
            R.drawable.inter_0040, R.drawable.inter_0041, R.drawable.inter_0042,
            R.drawable.inter_0043, R.drawable.inter_0044, R.drawable.inter_0045,
            R.drawable.inter_0046, R.drawable.inter_0047, R.drawable.inter_0048,
            R.drawable.inter_0049, R.drawable.inter_0050, R.drawable.inter_0051,
            R.drawable.inter_0052, R.drawable.inter_0053, R.drawable.inter_0054,
            R.drawable.inter_0055, R.drawable.inter_0056, R.drawable.inter_0057,
            R.drawable.inter_0058, R.drawable.inter_0059, R.drawable.inter_0060,
            R.drawable.inter_0061, R.drawable.inter_0062, R.drawable.inter_0063,
            R.drawable.inter_0064, R.drawable.inter_0065, R.drawable.inter_0066,
            R.drawable.inter_0067, R.drawable.inter_0068, R.drawable.inter_0069,
            R.drawable.inter_0070, R.drawable.inter_0071, R.drawable.inter_0072,
            R.drawable.inter_0073, R.drawable.inter_0074, R.drawable.inter_0075,
            R.drawable.inter_0076, R.drawable.inter_0077, R.drawable.inter_0078,
            R.drawable.inter_0079, R.drawable.inter_0080, R.drawable.inter_0081,
            R.drawable.inter_0082, R.drawable.inter_0083, R.drawable.inter_0084
    };

    private static final int ANIMATION_INTERVAL = 130;  // 100ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyac_night_alarm);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFasterAnimationsContainer.stop();
                finish();       // ?? 초후 닫아버림
            }
        }, 10500);
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus){
            ImageView imageView = (ImageView) findViewById(R.id.alyac_night_animation_imageview);
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
