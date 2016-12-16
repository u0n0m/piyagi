package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onedayone.piyagi.R;

import static java.lang.Integer.parseInt;

public class AlyacActivity extends AppCompatActivity {
    static final String FILE_NAME = "alyac_settings.value";
    Integer alyac_hour = 1;
    Integer alyac_minute = 10;
    Integer hour1;
    Integer minute1;
    Integer total_minutes;
    String on_off;
    String desc;
    String alyac_settings;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alyac_main);

        ImageButton btn_alyac = (ImageButton) findViewById(R.id.btn_alyac2);
        btn_alyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RepeatActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_hospital = (ImageButton) findViewById(R.id.btn_hospital2);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HospitalActivity.class);
                startActivity(intent);
            }
        });


        //시간 올리기 버튼(1시간 단위로)
        ImageButton btn_alyac_hour_up = (ImageButton) findViewById(R.id.btn_alyac_hour_up);
        btn_alyac_hour_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_hour);
                if(alyac_hour == 24 ) {
                    alyac_hour = 0;
                }
                else{
                    alyac_hour += 1;
                }
                tv.setText(String.valueOf(alyac_hour));
            }
        });

        //시간 내리기 버튼(1시간 단위로)
        ImageButton btn_alyac_hour_down = (ImageButton) findViewById(R.id.btn_alyac_hour_down);
        btn_alyac_hour_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_hour);
                if(alyac_hour == 0 ) {
                    alyac_hour = 24;
                }
                else{
                    alyac_hour -= 1;
                }
                //Toast.makeText(getApplicationContext(),"시간 내림 버튼 누름!",Toast.LENGTH_SHORT).show();
                tv.setText(String.valueOf(alyac_hour));
            }
        });

        //분 올리기 버튼(10분 단위로)
        ImageButton btn_alyac_minute_up = (ImageButton) findViewById(R.id.btn_alyac_minute_up);
        btn_alyac_minute_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_minute);
                if(alyac_minute >= 50 ) {
                    alyac_minute = 0;
                }
                else{
                    alyac_minute += 10;
                }
                tv.setText(String.valueOf(alyac_minute));
            }
        });

        //분 내리기 버튼(10분 단위로)
        ImageButton btn_alyac_minute_down = (ImageButton) findViewById(R.id.btn_alyac_minute_down);
        btn_alyac_minute_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv = (TextView) findViewById(R.id.textview_alyac_minute);
                if(alyac_minute <= 0 ) {
                    alyac_minute = 50;
                }
                else{
                    alyac_minute -= 10;
                }
                tv.setText(String.valueOf(alyac_minute));
            }
        });

        //저장 버튼 클릭
        ImageButton btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"save 클릭됨!",Toast.LENGTH_SHORT).show();
                save_alyac_settings();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
        ImageButton btn_cancel = (ImageButton) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"cancel 클릭됨!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    public boolean save_alyac_settings(){ // 저장버튼 누르면 실행되는 내용

        TextView textview_alyac_hour = (TextView) findViewById(R.id.textview_alyac_hour);  //알람 시간 읽어오기
        TextView textview_alyac_minute = (TextView) findViewById(R.id.textview_alyac_minute); // 알람 분 읽어오기
        Toast.makeText(getApplicationContext(),"save_alyac_settings!",Toast.LENGTH_SHORT).show();

        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("alyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
        Integer order = pref_alyac.getInt("order", 0); //알람 번호 읽어오기
        order ++;  // 알람 번호 증가시키기
        hour1 = parseInt(textview_alyac_hour.getText().toString());
        minute1 = parseInt(textview_alyac_minute.getText().toString());
        total_minutes = hour1 * 60 + minute1;
        on_off = "on"; //버튼 상태
        desc = "반복 복용";
        alyac_settings = order.toString() +":"+ total_minutes.toString() + ":" + desc + ":" + on_off ; //알람번호, 알람주기, 설명, 버튼 상태(on/off)

        SharedPreferences.Editor editor = pref_alyac.edit(); //sharedPreference 내용 수정
        editor.putInt("order",order);  // 알람 개수 저장
        editor.putString(order.toString(),alyac_settings); //알람 설정 저장
        editor.commit(); // 설정 적용

        Toast.makeText(getApplicationContext(), "order=" + order.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), "sharedPreference=" + alyac_settings, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RepeatService.class);
        intent.putExtra("total_minutes", String.valueOf(total_minutes));
        startService(intent);

        return true;
    }

}
