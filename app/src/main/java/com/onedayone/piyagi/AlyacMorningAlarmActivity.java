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

public class AlyacMorningAlarmActivity extends AppCompatActivity {
    FasterAnimationsContainer mFasterAnimationsContainer;
    private static final int[] IMAGE_RESOURCES = { R.drawable.morning_alarm_0000,
            R.drawable.morning_alarm_0001, R.drawable.morning_alarm_0002, R.drawable.morning_alarm_0003,
            R.drawable.morning_alarm_0004, R.drawable.morning_alarm_0005, R.drawable.morning_alarm_0006,
            R.drawable.morning_alarm_0007, R.drawable.morning_alarm_0008, R.drawable.morning_alarm_0009,
            R.drawable.morning_alarm_0010, R.drawable.morning_alarm_0011, R.drawable.morning_alarm_0012,
            R.drawable.morning_alarm_0013, R.drawable.morning_alarm_0014, R.drawable.morning_alarm_0015,
            R.drawable.morning_alarm_0016, R.drawable.morning_alarm_0017, R.drawable.morning_alarm_0018,
            R.drawable.morning_alarm_0019, R.drawable.morning_alarm_0020, R.drawable.morning_alarm_0021,
            R.drawable.morning_alarm_0022, R.drawable.morning_alarm_0023, R.drawable.morning_alarm_0024,
            R.drawable.morning_alarm_0025, R.drawable.morning_alarm_0026, R.drawable.morning_alarm_0027,
            R.drawable.morning_alarm_0028, R.drawable.morning_alarm_0029, R.drawable.morning_alarm_0030,
            R.drawable.morning_alarm_0031, R.drawable.morning_alarm_0032, R.drawable.morning_alarm_0033,
            R.drawable.morning_alarm_0034, R.drawable.morning_alarm_0035, R.drawable.morning_alarm_0036,
            R.drawable.morning_alarm_0037, R.drawable.morning_alarm_0038, R.drawable.morning_alarm_0039,
            R.drawable.morning_alarm_0040, R.drawable.morning_alarm_0041, R.drawable.morning_alarm_0042,
            R.drawable.morning_alarm_0043, R.drawable.morning_alarm_0044, R.drawable.morning_alarm_0045,
            R.drawable.morning_alarm_0046, R.drawable.morning_alarm_0047, R.drawable.morning_alarm_0048,
            R.drawable.morning_alarm_0049, R.drawable.morning_alarm_0050, R.drawable.morning_alarm_0051,
            R.drawable.morning_alarm_0052, R.drawable.morning_alarm_0053, R.drawable.morning_alarm_0054,
            R.drawable.morning_alarm_0055, R.drawable.morning_alarm_0056, R.drawable.morning_alarm_0057,
            R.drawable.morning_alarm_0058, R.drawable.morning_alarm_0059, R.drawable.morning_alarm_0060,
            R.drawable.morning_alarm_0061, R.drawable.morning_alarm_0062, R.drawable.morning_alarm_0063,
            R.drawable.morning_alarm_0064, R.drawable.morning_alarm_0065, R.drawable.morning_alarm_0066,
            R.drawable.morning_alarm_0067, R.drawable.morning_alarm_0068, R.drawable.morning_alarm_0069,
            R.drawable.morning_alarm_0070, R.drawable.morning_alarm_0071, R.drawable.morning_alarm_0072,
            R.drawable.morning_alarm_0073, R.drawable.morning_alarm_0074, R.drawable.morning_alarm_0075,
            R.drawable.morning_alarm_0076, R.drawable.morning_alarm_0077, R.drawable.morning_alarm_0078,
            R.drawable.morning_alarm_0079, R.drawable.morning_alarm_0080, R.drawable.morning_alarm_0081,
            R.drawable.morning_alarm_0082, R.drawable.morning_alarm_0083, R.drawable.morning_alarm_0084
    };

    private static final int ANIMATION_INTERVAL = 130;  // 100ms

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyac_morning_alarm);

        ImageButton btn_save = (ImageButton) findViewById(R.id.alyac_morning_alarm_btn);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFasterAnimationsContainer.stop();
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
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
            ImageView imageView = (ImageView) findViewById(R.id.alyac_morning_animation_imageview);
            mFasterAnimationsContainer = FasterAnimationsContainer
                    .getInstance(imageView);
            mFasterAnimationsContainer.addAllFrames(IMAGE_RESOURCES,
                    ANIMATION_INTERVAL);
            mFasterAnimationsContainer.start();
            Toast.makeText(this, "alarm:", Toast.LENGTH_LONG).show();

        } else {
            mFasterAnimationsContainer.stop();
            System.gc();
            //finish();
        }

    }
}
