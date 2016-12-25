package com.onedayone.piyagi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Integer repeat_hour = 1;
    Integer repeat_minute = 10;
    Integer hour1;
    Integer minute1;
    Integer total_minutes;
    String on_off;
    String desc;
    byte [] buf1;
    String repeat_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //startActivity(new Intent(this, Animate1Activity.class));

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        Toast.makeText(getApplicationContext(), "Width="+width+", Height="+height,Toast.LENGTH_SHORT).show();
        //Toast.makeText(getApplicationContext(), "ScreenSize="+getScreenSize(activity_main)

        RelativeLayout morning = (RelativeLayout) findViewById(R.id.main_alyac_menu1);
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacMorningSettingActivity.class);
                startActivity(intent);
                Handler hd = new Handler();
                hd.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        System.gc();
                        finish();       // 1 초후 이미지를 닫아버림
                    }
                }, 1000);
            }
        });
        RelativeLayout day = (RelativeLayout) findViewById(R.id.main_alyac_menu2);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacDaySettingActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        RelativeLayout evening = (RelativeLayout) findViewById(R.id.main_alyac_menu3);
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacEveningSettingActivity.class);
                startActivity(intent);
                //finish();
            }
        });
        RelativeLayout night = (RelativeLayout) findViewById(R.id.main_alyac_menu4);
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacNightSettingActivity.class);
                startActivity(intent);
                //finish();
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


    //화면 해상도 구해서 토스트 메시지로 띄워주기
    public Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return  size;
    }

}