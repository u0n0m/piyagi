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
        String ampm_setting = pref_alyac.getString("morning_ampm", "오전");
        String hour_setting = pref_alyac.getString("morning_hour", "07");
        String minute_setting = pref_alyac.getString("morning_minute", "30");
        String onoff_setting = pref_alyac.getString("morning_onoff", "꺼짐");
        //Toast.makeText(getApplicationContext(), "read2 morning_onoff)=" + morning_onoff_setting, Toast.LENGTH_LONG).show();
        // 설정 읽어서 복용 시간 표시
        String morning_settings = ampm_setting + " " + hour_setting + ":" + minute_setting;
        TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_datetime1);
        tv.setText(morning_settings);
        // 설정 읽어서 온오프 상태 표시
        Button btn = (Button) findViewById(R.id.alyac_morning_btn);
        btn.setText(onoff_setting);

        //알약 점심 복용 메뉴 표시
        ampm_setting = pref_alyac.getString("day_ampm", "오전");
        hour_setting = pref_alyac.getString("day_hour", "07");
        minute_setting = pref_alyac.getString("day_minute", "30");
        onoff_setting = pref_alyac.getString("day_onoff", "꺼짐");
        //Toast.makeText(getApplicationContext(), "read2 day_onoff)=" + day_onoff_setting, Toast.LENGTH_LONG).show();
        String day_settings = ampm_setting + " " + hour_setting + ":" + minute_setting;
        tv = (TextView) findViewById(R.id.textview_alyac_day_datetime1);
        tv.setText(day_settings);

        btn = (Button) findViewById(R.id.alyac_day_btn);
        btn.setText(onoff_setting);

        //알약 저녁 복용 메뉴 표시
        pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        ampm_setting = pref_alyac.getString("evening_ampm", "오전");
        hour_setting = pref_alyac.getString("evening_hour", "07");
        minute_setting = pref_alyac.getString("evening_minute", "30");
        onoff_setting = pref_alyac.getString("evening_onoff", "꺼짐");
        //Toast.makeText(getApplicationContext(), "read2 evening_onoff)=" + evening_onoff_setting, Toast.LENGTH_LONG).show();
        String evening_settings = ampm_setting + " " + hour_setting + ":" + minute_setting;
        tv = (TextView) findViewById(R.id.textview_alyac_evening_datetime1);
        tv.setText(evening_settings);
        btn = (Button) findViewById(R.id.alyac_evening_btn);
        btn.setText(onoff_setting);

        //알약 취침전 복용 메뉴 표시
        pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        ampm_setting = pref_alyac.getString("night_ampm", "오전");
        hour_setting = pref_alyac.getString("night_hour", "07");
        minute_setting = pref_alyac.getString("night_minute", "30");
        onoff_setting = pref_alyac.getString("night_onoff", "꺼짐");
        //Toast.makeText(getApplicationContext(), "read2 night_onoff)=" + night_onoff_setting, Toast.LENGTH_LONG).show();
        String night_settings = ampm_setting + " " + hour_setting + ":" + minute_setting;
        tv = (TextView) findViewById(R.id.textview_alyac_night_datetime1);
        tv.setText(night_settings);
        btn = (Button) findViewById(R.id.alyac_night_btn);
        btn.setText(onoff_setting);


        // 아침약 복용 예약 설정 버튼 기능 구현
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

        // 점심약 복용 예약 설정 버튼 기능 구현
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
        Button day_onoff = (Button) findViewById(R.id.alyac_day_btn);
        day_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button day_onoff = (Button) findViewById(R.id.alyac_day_btn);
                SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
                String day_onoff_setting = pref_alyac.getString("day_onoff", "복약분 설정안됨");
                if(day_onoff_setting.equals("켜짐") ) {
                    day_onoff.setText("꺼짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("day_onoff","꺼짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
                if(day_onoff_setting.equals("꺼짐") ) {
                    day_onoff.setText("켜짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("day_onoff","켜짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
            }
        });

        // 저녁약 복용 예약 설정 버튼 기능 구현
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
        Button evening_onoff = (Button) findViewById(R.id.alyac_evening_btn);
        evening_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button evening_onoff = (Button) findViewById(R.id.alyac_evening_btn);
                SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
                String evening_onoff_setting = pref_alyac.getString("evening_onoff", "복약분 설정안됨");
                if(evening_onoff_setting.equals("켜짐") ) {
                    evening_onoff.setText("꺼짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("evening_onoff","꺼짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
                if(evening_onoff_setting.equals("꺼짐") ) {
                    evening_onoff.setText("켜짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("evening_onoff","켜짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
            }
        });

        // 취침약 복용 예약 설정 버튼 기능 구현
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
        Button night_onoff = (Button) findViewById(R.id.alyac_night_btn);
        night_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button night_onoff = (Button) findViewById(R.id.alyac_night_btn);
                SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
                String night_onoff_setting = pref_alyac.getString("night_onoff", "복약분 설정안됨");
                if(night_onoff_setting.equals("켜짐") ) {
                    night_onoff.setText("꺼짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("night_onoff","꺼짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
                if(night_onoff_setting.equals("꺼짐") ) {
                    night_onoff.setText("켜짐");
                    pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
                    editor.putString("night_onoff","켜짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
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