package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Integer.parseInt;

public class RepeatActivity extends AppCompatActivity {
    static final String FILE_NAME = "repeat_settings.value";
    Integer repeat_hour = 1;
    Integer repeat_minute = 10;
    Integer hour1;
    Integer minute1;
    Integer total_minutes;
    String on_off;
    String desc;
    String repeat_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
        setContentView(R.layout.repeat_main);

        //복약 예약 버튼
        ImageButton btn_alyac = (ImageButton) findViewById(R.id.btn_alyac);
        btn_alyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacActivity.class);
                startActivity(intent);
            }
        });

        //병원 예약 버튼
        ImageButton btn_hospital = (ImageButton) findViewById(R.id.btn_hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HospitalActivity.class);
                startActivity(intent);
            }
        });

        //시간 올리기 버튼(1시간 단위로)
        ImageButton btn_repeat_hour_up = (ImageButton) findViewById(R.id.btn_repeat_hour_up);
        btn_repeat_hour_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_hour);
                if(repeat_hour == 24 ) {
                    repeat_hour = 0;
                }
                else{
                    repeat_hour += 1;
                }
                tv.setText(String.valueOf(repeat_hour));
            }
        });

        //시간 내리기 버튼(1시간 단위로)
        ImageButton btn_repeat_hour_down = (ImageButton) findViewById(R.id.btn_repeat_hour_down);
        btn_repeat_hour_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_repeat_hour);
                if(repeat_hour == 0 ) {
                    repeat_hour = 24;
                }
                else{
                    repeat_hour -= 1;
                }
                //Toast.makeText(getApplicationContext(),"시간 내림 버튼 누름!",Toast.LENGTH_SHORT).show();
                tv.setText(String.valueOf(repeat_hour));
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
                }
                else{
                    repeat_minute += 10;
                }
                tv.setText(String.valueOf(repeat_minute));
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
                }
                else{
                    repeat_minute -= 10;
                }
                tv.setText(String.valueOf(repeat_minute));
            }
        });

        //저장 버튼 클릭
        ImageButton btn_save = (ImageButton) findViewById(R.id.repeat_btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"save 클릭됨!",Toast.LENGTH_SHORT).show();
                save_repeat_settings();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        ImageButton btn_cancel = (ImageButton) findViewById(R.id.repeat_btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"cancel 클릭됨!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public boolean save_repeat_settings(){ // 저장버튼 누르면 실행되는 내용

        TextView textview_repeat_hour = (TextView) findViewById(R.id.textview_repeat_hour);  //알람 시간 읽어오기
        TextView textview_repeat_minute = (TextView) findViewById(R.id.textview_repeat_minute); // 알람 분 읽어오기
        Toast.makeText(getApplicationContext(),"save_repeat_settings!",Toast.LENGTH_SHORT).show();

        SharedPreferences pref_repeat = getApplicationContext().getSharedPreferences("repeatSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
        Integer order = pref_repeat.getInt("order", 0); //알람 번호 읽어오기
        order ++;  // 알람 번호 증가시키기
        hour1 = parseInt(textview_repeat_hour.getText().toString());
        minute1 = parseInt(textview_repeat_minute.getText().toString());
        total_minutes = hour1 * 60 + minute1;
        on_off = "on"; //버튼 상태
        desc = "반복 복용";
        repeat_settings = order.toString() +":"+ total_minutes.toString() + ":" + desc + ":" + on_off ; //알람번호, 알람주기, 설명, 버튼 상태(on/off)

        SharedPreferences.Editor editor = pref_repeat.edit(); //sharedPreference 내용 수정
        editor.putInt("order",order);  // 알람 개수 저장
        editor.putString(order.toString(),repeat_settings); //알람 설정 저장
        editor.commit(); // 설정 적용

        Toast.makeText(getApplicationContext(), "order=" + order.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "sharedPreference=" + repeat_settings, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RepeatService.class);
        intent.putExtra("total_minutes", String.valueOf(total_minutes));
        startService(intent);

        return true;
    }

}
