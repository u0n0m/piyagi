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

import static com.onedayone.piyagi.R.id.alyac_morning_btn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //알약 아침 복용 메뉴 표시
        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        String morning_ampm_setting = pref_alyac.getString("morning_ampm", "오전오후 설정안됨");
        String morning_hour_setting = pref_alyac.getString("morning_hour", "복약시 설정안됨");
        String morning_minute_setting = pref_alyac.getString("morning_minute", "복약분 설정안됨");
        final String morning_onoff_setting = pref_alyac.getString("morning_onoff", "설정상태 설정안됨");
        Toast.makeText(getApplicationContext(), "read2 morning_onoff)=" + morning_onoff_setting, Toast.LENGTH_LONG).show();

        // 설정 읽어서 복용 시간 표시
        String morning_settings = morning_ampm_setting + " " + morning_hour_setting + ":" + morning_minute_setting;
        TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_datetime1);
        tv.setText(morning_settings);

        // 설정 읽어서 온오프 상태 표시
        Button btn = (Button) findViewById(alyac_morning_btn);
        btn.setText(morning_onoff_setting);

        RelativeLayout morning = (RelativeLayout) findViewById(R.id.main_alyac_morning_menu);
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacMorningSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        Button morning_onoff = (Button) findViewById(R.id.alyac_morning_btn);
        morning_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button morning_onoff = (Button) findViewById(R.id.alyac_morning_btn);
                SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
                String morning_onoff_setting = pref_alyac.getString("morning_onoff", "복약분 설정안됨");
                if(morning_onoff_setting.equals("켜짐") ) {
                    morning_onoff.setText("꺼짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("morning_onoff","꺼짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
                if(morning_onoff_setting.equals("꺼짐") ) {
                    morning_onoff.setText("켜짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("morning_onoff","켜짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
            }
        });
        RelativeLayout day = (RelativeLayout) findViewById(R.id.main_alyac_day_menu);
        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacDaySettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        RelativeLayout evening = (RelativeLayout) findViewById(R.id.main_alyac_evening_menu);
        evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacEveningSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        RelativeLayout night = (RelativeLayout) findViewById(R.id.main_alyac_night_menu);
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