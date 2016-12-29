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
import android.widget.Toast;

public class HospitalAlarmActivity extends AppCompatActivity {
    FasterAnimationsContainer mFasterAnimationsContainer;
    private static final int[] IMAGE_RESOURCES = { R.drawable.hospital_now_00001,
            R.drawable.hospital_now_00001, R.drawable.hospital_now_00002, R.drawable.hospital_now_00003,
            R.drawable.hospital_now_00004, R.drawable.hospital_now_00005, R.drawable.hospital_now_00006,
            R.drawable.hospital_now_00007, R.drawable.hospital_now_00008, R.drawable.hospital_now_00009,
            R.drawable.hospital_now_00010, R.drawable.hospital_now_00011, R.drawable.hospital_now_00012,
            R.drawable.hospital_now_00013, R.drawable.hospital_now_00014, R.drawable.hospital_now_00015,
            R.drawable.hospital_now_00016, R.drawable.hospital_now_00017, R.drawable.hospital_now_00018,
            R.drawable.hospital_now_00019, R.drawable.hospital_now_00020, R.drawable.hospital_now_00021,
            R.drawable.hospital_now_00022, R.drawable.hospital_now_00023, R.drawable.hospital_now_00024,
            R.drawable.hospital_now_00025, R.drawable.hospital_now_00026, R.drawable.hospital_now_00027,
            R.drawable.hospital_now_00028, R.drawable.hospital_now_00029, R.drawable.hospital_now_00030,
            R.drawable.hospital_now_00031, R.drawable.hospital_now_00032, R.drawable.hospital_now_00033,
            R.drawable.hospital_now_00034, R.drawable.hospital_now_00035, R.drawable.hospital_now_00036,
            R.drawable.hospital_now_00037, R.drawable.hospital_now_00038, R.drawable.hospital_now_00039,
            R.drawable.hospital_now_00040, R.drawable.hospital_now_00041, R.drawable.hospital_now_00042,
            R.drawable.hospital_now_00043, R.drawable.hospital_now_00044, R.drawable.hospital_now_00045,
            R.drawable.hospital_now_00046, R.drawable.hospital_now_00047, R.drawable.hospital_now_00048,
            R.drawable.hospital_now_00049, R.drawable.hospital_now_00050, R.drawable.hospital_now_00051,
            R.drawable.hospital_now_00052, R.drawable.hospital_now_00053, R.drawable.hospital_now_00054,
            R.drawable.hospital_now_00055, R.drawable.hospital_now_00056, R.drawable.hospital_now_00057,
            R.drawable.hospital_now_00058, R.drawable.hospital_now_00059, R.drawable.hospital_now_00060,
            R.drawable.hospital_now_00061, R.drawable.hospital_now_00062, R.drawable.hospital_now_00063,
            R.drawable.hospital_now_00064, R.drawable.hospital_now_00065, R.drawable.hospital_now_00066,
            R.drawable.hospital_now_00067, R.drawable.hospital_now_00068, R.drawable.hospital_now_00069,
            R.drawable.hospital_now_00070, R.drawable.hospital_now_00071, R.drawable.hospital_now_00072,
            R.drawable.hospital_now_00073, R.drawable.hospital_now_00074, R.drawable.hospital_now_00075,
            R.drawable.hospital_now_00076, R.drawable.hospital_now_00077, R.drawable.hospital_now_00078,
            R.drawable.hospital_now_00079
    };

    private static final int ANIMATION_INTERVAL = 130;  // 100ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_now_alarm);

        ImageButton btn_save = (ImageButton) findViewById(R.id.hospital_now_alarm_btn);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFasterAnimationsContainer.stop();
                System.gc();
                finish();
            }
        });
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
            ImageView imageView = (ImageView) findViewById(R.id.hospital_now_animation_imageview);
            mFasterAnimationsContainer = FasterAnimationsContainer
                    .getInstance(imageView);
            mFasterAnimationsContainer.addAllFrames(IMAGE_RESOURCES, ANIMATION_INTERVAL);
            mFasterAnimationsContainer.start();
            Toast.makeText(this, "alarm:", Toast.LENGTH_LONG).show();
        } else {
            mFasterAnimationsContainer.stop();
            System.gc();
            finish();
        }

    }
}
