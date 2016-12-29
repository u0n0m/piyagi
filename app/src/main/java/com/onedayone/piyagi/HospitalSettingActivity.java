package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Integer.parseInt;

public class HospitalSettingActivity extends AppCompatActivity {
    Integer hospital_year = 2016;
    Integer hospital_month = 12;
    Integer hospital_day = 30;
    Boolean hospital_ampm = true;  // true:오전, false: 오후
    Integer hospital_hour = 1;
    Integer hospital_minute = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_setting);

        //저장 버튼 클릭
        Button btn_save = (Button) findViewById(R.id.hospital_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"save 클릭됨!",Toast.LENGTH_SHORT).show();
                save_hospital_settings();
                startActivity(new Intent(getApplicationContext(), HospitalAlarmActivity.class));
                System.gc();
                finish();
            }
        });

        //년도 변경 위쪽 버튼
        ImageButton btn_hospital_year_up = (ImageButton) findViewById(R.id.btn_hospital_year_up);
        btn_hospital_year_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_year);
                hospital_year += 1 ;
                tv.setText(hospital_year.toString());
            }
        });
        //년도 변경 아래쪽 버튼
        ImageButton btn_hospital_year_down = (ImageButton) findViewById(R.id.btn_hospital_year_down);
        btn_hospital_year_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_year);
                hospital_year -= 1 ;
                tv.setText(hospital_year.toString());
            }
        });

        //월 올리기 버튼
        ImageButton btn_hospital_month_up = (ImageButton) findViewById(R.id.btn_hospital_month_up);
        btn_hospital_month_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_month);
                if(hospital_month == 12 ) {
                    hospital_month = 1;
                }
                else{
                    hospital_month += 1;
                }
                tv.setText(String.valueOf(hospital_month));
            }
        });

        //월 내리기 버튼
        ImageButton btn_hospital_month_down = (ImageButton) findViewById(R.id.btn_hospital_month_down);
        btn_hospital_month_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_month);
                if(hospital_month == 1 ) {
                    hospital_month = 12;
                }
                else{
                    hospital_month -= 1;
                }
                tv.setText(String.valueOf(hospital_month));
            }
        });

        //일 올리기 버튼
        ImageButton btn_hospital_day_up = (ImageButton) findViewById(R.id.btn_hospital_day_up);
        btn_hospital_day_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_day);
                if(hospital_day >= 31 ) {
                    hospital_day= 1;
                    tv.setText("0" + String.valueOf(hospital_day));
                }
                else{
                    hospital_day += 1;
                    tv.setText(String.valueOf(hospital_day));
                }
            }
        });

        //일 내리기 버튼(1일 단위로)
        ImageButton btn_hospital_day_down = (ImageButton) findViewById(R.id.btn_hospital_day_down);
        btn_hospital_day_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_day);
                if(hospital_day == 1 ) {
                    hospital_day = 31;
                    tv.setText(String.valueOf(hospital_day));
                }
                else if(hospital_day < 10){
                    hospital_day -= 1;
                    tv.setText(String.valueOf("0" + hospital_day));
                }
                else{
                    hospital_day -= 1;
                    tv.setText(String.valueOf(hospital_day));
                }
            }
        });


        //////////////////////////////////////////////////////////////////////////////////


        //오전/오후 변경 위쪽 버튼
        ImageButton btn_hospital_ampm_up = (ImageButton) findViewById(R.id.btn_hospital_ampm_up);
        btn_hospital_ampm_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_ampm);
                if(hospital_ampm == true ) {
                    hospital_ampm = false;
                    tv.setText("오후");
                }
                else{
                    hospital_ampm = true;
                    tv.setText("오전");
                }
            }
        });
        //오전/오후 변경 아래쪽 버튼
        ImageButton btn_hospital_ampm_down = (ImageButton) findViewById(R.id.btn_hospital_ampm_down);
        btn_hospital_ampm_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_ampm);
                if(hospital_ampm == true ) {
                    hospital_ampm = false;
                    tv.setText("오후");
                }
                else{
                    hospital_ampm = true;
                    tv.setText("오전");
                }
            }
        });

        //시간 올리기 버튼(1시간 단위로)
        ImageButton btn_hospital_hour_up = (ImageButton) findViewById(R.id.btn_hospital_hour_up);
        btn_hospital_hour_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_hour);
                if(hospital_hour == 12 ) {
                    hospital_hour = 1;
                }
                else{
                    hospital_hour += 1;
                }
                if(hospital_hour >= 1 && hospital_hour <=9 ){
                    tv.setText("0" + String.valueOf(hospital_hour));
                }
                else {
                    tv.setText(String.valueOf(hospital_hour));
                }
            }
        });

        //시간 내리기 버튼(1시간 단위로)
        ImageButton btn_hospital_hour_down = (ImageButton) findViewById(R.id.btn_hospital_hour_down);
        btn_hospital_hour_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_hour);
                if(hospital_hour == 1 ) {
                    hospital_hour = 12;
                }
                else{
                    hospital_hour -= 1;
                }
                if(hospital_hour >= 1 && hospital_hour <=9 ){
                    tv.setText("0" + String.valueOf(hospital_hour));
                }
                else {
                    tv.setText(String.valueOf(hospital_hour));
                }
            }
        });

        //분 올리기 버튼(10분 단위로)
        ImageButton btn_hospital_minute_up = (ImageButton) findViewById(R.id.btn_hospital_minute_up);
        btn_hospital_minute_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_minute);
                if(hospital_minute >= 50 ) {
                    hospital_minute = 0;
                    tv.setText("0" + String.valueOf(hospital_minute));
                }
                else{
                    hospital_minute += 10;
                    tv.setText(String.valueOf(hospital_minute));
                }

            }
        });

        //분 내리기 버튼(10분 단위로)
        ImageButton btn_hospital_minute_down = (ImageButton) findViewById(R.id.btn_hospital_minute_down);
        btn_hospital_minute_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_hospital_minute);
                if(hospital_minute <= 0 ) {
                    hospital_minute = 50;
                    tv.setText(String.valueOf(hospital_minute));
                }
                else if(hospital_minute == 10){
                    hospital_minute -= 10;
                    tv.setText(String.valueOf("0" + hospital_minute));
                }
                else{
                    hospital_minute -= 10;
                    tv.setText(String.valueOf(hospital_minute));
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

    public boolean save_hospital_settings(){ // 저장버튼 누르면 실행되는 내용
        TextView textview_hospital_year = (TextView) findViewById(R.id.textview_hospital_year);  //알람 오전/오후 읽어오기
        TextView textview_hospital_month = (TextView) findViewById(R.id.textview_hospital_month);  //알람 시간 읽어오기
        TextView textview_hospital_day = (TextView) findViewById(R.id.textview_hospital_day); // 알람 분 읽어오기
        TextView textview_hospital_ampm = (TextView) findViewById(R.id.textview_hospital_ampm);  //알람 오전/오후 읽어오기
        TextView textview_hospital_hour = (TextView) findViewById(R.id.textview_hospital_hour);  //알람 시간 읽어오기
        TextView textview_hospital_minute = (TextView) findViewById(R.id.textview_hospital_minute); // 알람 분 읽어오기
        EditText edittext_hospital_description = (EditText) findViewById(R.id.edittext_hospital_description); // 알람 분 읽어오기

        String year = textview_hospital_year.getText().toString();
        String month = textview_hospital_month.getText().toString();
        String day = textview_hospital_day.getText().toString();
        String ampm = textview_hospital_ampm.getText().toString();
        String hour = textview_hospital_hour.getText().toString();
        String minute = textview_hospital_minute.getText().toString();
        String onoff = "켜짐"; //설정 상태
        String description = edittext_hospital_description.getText().toString();

        SharedPreferences pref_hospital = getApplicationContext().getSharedPreferences("HospitalSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
        SharedPreferences.Editor editor = pref_hospital.edit(); //sharedPreference 내용 수정
        editor.putString("hospital_year",year); //년도 설정 저장
        editor.putString("hospital_month",month); //월 설정 저장
        editor.putString("hospital_day",day); //일 설정 저장
        editor.putString("hospital_ampm",ampm); //오전/오후 설정 저장
        editor.putString("hospital_hour",hour); //시간 설정 저장
        editor.putString("hospital_minute",minute); //분 설정 저장
        editor.putString("hospital_onoff",onoff); //켜짐/꺼짐 설정 저장
        editor.putString("hospital_description",description); //켜짐/꺼짐 설정 저장
        editor.commit(); // 설정 적용

        Intent intent = new Intent(this, HospitalService.class);
        startService(intent);

        return true;
    }
}
