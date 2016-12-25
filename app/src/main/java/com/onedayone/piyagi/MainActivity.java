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

        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        String morning_settings = pref_alyac.getString("morning", "설정 안됨");
        Toast.makeText(getApplicationContext(), "read2 alyacSettings > morning)=" + morning_settings, Toast.LENGTH_LONG).show();
        TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_datetime1);
        tv.setText(morning_settings);

        RelativeLayout morning = (RelativeLayout) findViewById(R.id.main_alyac_menu1);
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacMorningSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();

            }
        });
        RelativeLayout day = (RelativeLayout) findViewById(R.id.main_alyac_menu2);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacDaySettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        RelativeLayout evening = (RelativeLayout) findViewById(R.id.main_alyac_menu3);
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacEveningSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        RelativeLayout night = (RelativeLayout) findViewById(R.id.main_alyac_menu4);
        night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacNightSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
        //finish();
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

}