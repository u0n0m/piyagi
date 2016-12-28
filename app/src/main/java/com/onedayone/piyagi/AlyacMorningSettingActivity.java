package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Integer.parseInt;

public class AlyacMorningSettingActivity extends AppCompatActivity {
    Boolean alyac_ampm = true;  // true:오전, false: 오후
    Integer alyac_hour = 1;
    Integer alyac_minute = 10;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyac_morning_setting);

        //저장 버튼 클릭
        Button btn_save = (Button) findViewById(R.id.alyac_morning_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"save 클릭됨!",Toast.LENGTH_SHORT).show();
                save_alyac_morning_settings();
                //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                System.gc();
                finish();
            }
        });

        //오전/오후 변경 위쪽 버튼
        ImageButton btn_alyac_morning_ampm_up = (ImageButton) findViewById(R.id.btn_alyac_morning_ampm_up);
        btn_alyac_morning_ampm_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_ampm);
                if(alyac_ampm == true ) {
                    alyac_ampm = false;
                    tv.setText("오후");
                }
                else{
                    alyac_ampm = true;
                    tv.setText("오전");
                }
            }
        });
        //오전/오후 변경 아래쪽 버튼
        ImageButton btn_alyac_morning_ampm_down = (ImageButton) findViewById(R.id.btn_alyac_morning_ampm_down);
        btn_alyac_morning_ampm_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_ampm);
                if(alyac_ampm == true ) {
                    alyac_ampm = false;
                    tv.setText("오후");
                }
                else{
                    alyac_ampm = true;
                    tv.setText("오전");
                }
            }
        });

        //시간 올리기 버튼(1시간 단위로)
        ImageButton btn_alyac_morning_hour_up = (ImageButton) findViewById(R.id.btn_alyac_morning_hour_up);
        btn_alyac_morning_hour_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_hour);
                if(alyac_hour == 12 ) {
                    alyac_hour = 0;
                }
                else{
                    alyac_hour += 1;
                }

                if(alyac_hour >= 0 && alyac_hour <=9 ){
                    tv.setText("0" + String.valueOf(alyac_hour));
                }
                else {
                    tv.setText(String.valueOf(alyac_hour));
                }

            }
        });

        //시간 내리기 버튼(1시간 단위로)
        ImageButton btn_alyac_morning_hour_down = (ImageButton) findViewById(R.id.btn_alyac_morning_hour_down);
        btn_alyac_morning_hour_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_hour);
                if(alyac_hour == 0 ) {
                    alyac_hour = 12;
                }
                else{
                    alyac_hour -= 1;
                }

                if(alyac_hour >= 0 && alyac_hour <=9 ){
                    tv.setText("0" + String.valueOf(alyac_hour));
                }
                else {
                    tv.setText(String.valueOf(alyac_hour));
                }
            }
        });

        //분 올리기 버튼(10분 단위로)
        ImageButton btn_alyac_morning_minute_up = (ImageButton) findViewById(R.id.btn_alyac_morning_minute_up);
        btn_alyac_morning_minute_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_minute);
                if(alyac_minute >= 50 ) {
                    alyac_minute = 0;
                    tv.setText("0" + String.valueOf(alyac_minute));
                }
                else{
                    alyac_minute += 1;
                    tv.setText(String.valueOf(alyac_minute));
                }

            }
        });

        //분 내리기 버튼(10분 단위로)
        ImageButton btn_alyac_morning_minute_down = (ImageButton) findViewById(R.id.btn_alyac_morning_minute_down);
        btn_alyac_morning_minute_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_morning_minute);
                if(alyac_minute <= 0 ) {
                    alyac_minute = 50;
                    tv.setText(String.valueOf(alyac_minute));
                }
                else if(alyac_minute == 10){
                    alyac_minute -= 1;
                    tv.setText(String.valueOf("0" + alyac_minute));
                }
                else{
                    alyac_minute -= 1;
                    tv.setText(String.valueOf(alyac_minute));
                }

            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        System.gc();
        finish();
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

    public boolean save_alyac_morning_settings(){ // 저장버튼 누르면 실행되는 내용
        TextView textview_alyac_morning_ampm = (TextView) findViewById(R.id.textview_alyac_morning_ampm);  //알람 오전/오후 읽어오기
        TextView textview_alyac_morning_hour = (TextView) findViewById(R.id.textview_alyac_morning_hour);  //알람 시간 읽어오기
        TextView textview_alyac_morning_minute = (TextView) findViewById(R.id.textview_alyac_morning_minute); // 알람 분 읽어오기
        //Toast.makeText(getApplicationContext(),"save_alyac_settings!",Toast.LENGTH_SHORT).show();

        String ampm = textview_alyac_morning_ampm.getText().toString();
        String hour = textview_alyac_morning_hour.getText().toString();
        String minute = textview_alyac_morning_minute.getText().toString();
        String onoff = "켜짐"; //설정 상태
        //String alyac_morning_settings = ampm + ":" + hour + ":" + minute + ":" + onoff ; //오전/오후, 알람시간, 알람분, 설정 상태(on/off)
        //Toast.makeText(getApplicationContext(), "read variable alyac_morning_settings =" + alyac_morning_settings, Toast.LENGTH_LONG).show();

        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
        SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
        editor.putString("morning_ampm",ampm); //아침약 알람 설정 저장
        editor.putString("morning_hour",hour); //아침약 알람 설정 저장
        editor.putString("morning_minute",minute); //아침약 알람 설정 저장
        editor.putString("morning_onoff",onoff); //아침약 알람 설정 저장
        editor.commit(); // 설정 적용
/*
        pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        String morning_ampm = pref_alyac.getString("morning_ampm", "오전/오후 설정안됨");
        Toast.makeText(getApplicationContext(), "read1 morning_ampm)=" + morning_ampm, Toast.LENGTH_LONG).show();
*/
        Intent intent = new Intent(this, AlyacMorningService.class);
//        if(ampm.equals("오후")){
//            hour = hour  + 12; // 알람 매니저에 24시간 체계로 시간을 입력해주기 위해서...
//        }
//        intent.putExtra("hour", parseInt(hour));
//        intent.putExtra("minute", parseInt(minute));
        startService(intent);

        return true;
    }
}
