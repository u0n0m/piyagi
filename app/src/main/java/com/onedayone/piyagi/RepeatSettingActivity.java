package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RepeatSettingActivity extends AppCompatActivity {
    Boolean repeat_ampm = true;  // true:오전, false: 오후
    Integer repeat_hour = 1;
    Integer repeat_minute = 10;
    Integer repeat_period = 4;

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
        //finish();
    }
    @Override
    protected void onStop() {
        System.gc();
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_setting);

        //저장 버튼 클릭
        Button btn_save = (Button) findViewById(R.id.repeat_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save_repeat_settings();
                System.gc();
                finish();
            }
        });

        //오전/오후 변경 위쪽 버튼
        ImageButton btn_repeat_ampm_up = (ImageButton) findViewById(R.id.btn_repeat_ampm_up);
        btn_repeat_ampm_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_ampm);
                if(repeat_ampm == true ) {
                    repeat_ampm = false;
                    tv.setText("오후");
                }
                else{
                    repeat_ampm = true;
                    tv.setText("오전");
                }
            }
        });
        //오전/오후 변경 아래쪽 버튼
        ImageButton btn_repeat_ampm_down = (ImageButton) findViewById(R.id.btn_repeat_ampm_down);
        btn_repeat_ampm_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_ampm);
                if(repeat_ampm == true ) {
                    repeat_ampm = false;
                    tv.setText("오후");
                }
                else{
                    repeat_ampm = true;
                    tv.setText("오전");
                }
            }
        });

        //시간 올리기 버튼(1시간 단위로)
        ImageButton btn_repeat_hour_up = (ImageButton) findViewById(R.id.btn_repeat_hour_up);
        btn_repeat_hour_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_hour);
                if(repeat_hour == 12 ) {
                    repeat_hour = 0;
                }
                else{
                    repeat_hour += 1;
                }
                if(repeat_hour >= 0 && repeat_hour <=9 ){
                    tv.setText("0" + String.valueOf(repeat_hour));
                }
                else {
                    tv.setText(String.valueOf(repeat_hour));
                }
            }
        });

        //시간 내리기 버튼(1시간 단위로)
        ImageButton btn_repeat_hour_down = (ImageButton) findViewById(R.id.btn_repeat_hour_down);
        btn_repeat_hour_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_hour);
                if(repeat_hour == 0 ) {
                    repeat_hour = 12;
                }
                else{
                    repeat_hour -= 1;
                }
                if(repeat_hour >= 0 && repeat_hour <=9 ){
                    tv.setText("0" + String.valueOf(repeat_hour));
                }
                else {
                    tv.setText(String.valueOf(repeat_hour));
                }
            }
        });

        //분 올리기 버튼(10분 단위로)
        ImageButton btn_repeat_minute_up = (ImageButton) findViewById(R.id.btn_repeat_minute_up);
        btn_repeat_minute_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_minute);
                if(repeat_minute >= 50 ) {
                    repeat_minute = 0;
                    tv.setText("0" + String.valueOf(repeat_minute));
                }
                else{
                    repeat_minute += 1;
                    tv.setText(String.valueOf(repeat_minute));
                }

            }
        });

        //분 내리기 버튼(10분 단위로)
        ImageButton btn_repeat_minute_down = (ImageButton) findViewById(R.id.btn_repeat_minute_down);
        btn_repeat_minute_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_minute);
                if(repeat_minute <= 0 ) {
                    repeat_minute = 50;
                    tv.setText(String.valueOf(repeat_minute));
                }
                else if(repeat_minute == 10){
                    repeat_minute -= 1;
                    tv.setText(String.valueOf("0" + repeat_minute));
                }
                else{
                    repeat_minute -= 1;
                    tv.setText(String.valueOf(repeat_minute));
                }

            }
        });

        //주기 올리기 버튼(1시간 단위로)
        ImageButton btn_repeat_period_up = (ImageButton) findViewById(R.id.btn_repeat_period_up);
        btn_repeat_period_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_period);
                if(repeat_period == 12 ) {
                    repeat_period = 0;
                }
                else{
                    repeat_period += 1;
                }
                if(repeat_period >= 0 && repeat_period <=9 ){
                    tv.setText("0" + String.valueOf(repeat_period));
                }
                else {
                    tv.setText(String.valueOf(repeat_period));
                }
            }
        });

        //주기 내리기 버튼(1시간 단위로)
        ImageButton btn_repeat_period_down = (ImageButton) findViewById(R.id.btn_repeat_period_down);
        btn_repeat_period_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_period);
                if(repeat_period == 0 ) {
                    repeat_period = 12;
                }
                else{
                    repeat_period -= 1;
                }

                if(repeat_period >= 0 && repeat_period <=9 ){
                    tv.setText("0" + String.valueOf(repeat_period));
                }
                else {
                    tv.setText(String.valueOf(repeat_period));
                }
            }
        });
    }

    public boolean save_repeat_settings(){ // 저장버튼 누르면 실행되는 내용
        TextView textview_repeat_ampm = (TextView) findViewById(R.id.textview_repeat_ampm);  //알람 오전/오후 읽어오기
        TextView textview_repeat_hour = (TextView) findViewById(R.id.textview_repeat_hour);  //알람 시간 읽어오기
        TextView textview_repeat_minute = (TextView) findViewById(R.id.textview_repeat_minute); // 알람 분 읽어오기
        TextView textview_repeat_period = (TextView) findViewById(R.id.textview_repeat_period); // 알람 분 읽어오기

        String ampm = textview_repeat_ampm.getText().toString();
        String hour = textview_repeat_hour.getText().toString();
        String minute = textview_repeat_minute.getText().toString();
        String onoff = "켜짐"; //설정 상태
        String period = textview_repeat_period.getText().toString();

        SharedPreferences pref_repeat = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
        SharedPreferences.Editor editor = pref_repeat.edit(); //sharedPreference 내용 수정
        editor.putString("repeat_ampm",ampm); //아침약 알람 설정 저장
        editor.putString("repeat_hour",hour); //아침약 알람 설정 저장
        editor.putString("repeat_minute",minute); //아침약 알람 설정 저장
        editor.putString("repeat_onoff",onoff); //아침약 알람 설정 저장
        editor.putString("repeat_period",period); //아침약 알람 설정 저장
        editor.commit(); // 설정 적용

        Intent intent = new Intent(this, RepeatService.class);
        startService(intent);

        return true;
    }
}
