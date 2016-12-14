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
import com.onedayone.piyagi.R;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import static java.lang.Integer.min;
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
        ImageButton btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"save 클릭됨!",Toast.LENGTH_SHORT).show();
                save_repeat_settings();
            }
        });
    }

    public boolean save_repeat_settings(){

        TextView textview_repeat_hour = (TextView) findViewById(R.id.textview_repeat_hour);
        TextView textview_repeat_minute = (TextView) findViewById(R.id.textview_repeat_minute);
        Toast.makeText(getApplicationContext(),"save_repeat_settings!",Toast.LENGTH_SHORT).show();
//        try {
//            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            hour1 = parseInt(textview_repeat_hour.getText().toString());
//            minute1 = parseInt(textview_repeat_minute.getText().toString());
//            total_minutes = hour1 * 60 + minute1;
//            on_off = ":on";
//            desc = "반복 복용";
//            repeat_settings = total_minutes.toString() + on_off + desc ;
//            fos.write(repeat_settings.getBytes());
//            fos.close();
//            Toast.makeText(getApplicationContext(),repeat_settings+"파일에 저장 되었습니다!",Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Log.e("File", "에러=" + e);
//            Toast.makeText(getApplicationContext(),"설정 저장 실패!",Toast.LENGTH_SHORT).show();
//        }
//        try {
//
//            FileInputStream fis = openFileInput(FILE_NAME);
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            String str = new String(buffer);
//            Toast.makeText(getApplicationContext(), str+"읽음",Toast.LENGTH_SHORT).show();
//            fis.close();
//
//        } catch (Exception e) {
//            Log.e("File", "에러=" + e);
//        }
            hour1 = parseInt(textview_repeat_hour.getText().toString());
            minute1 = parseInt(textview_repeat_minute.getText().toString());
            total_minutes = hour1 * 60 + minute1;
            on_off = ":on";
            desc = "반복 복용";
            repeat_settings = total_minutes.toString() + on_off + desc ;
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PrefName", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("test",repeat_settings);//token, text);
        editor.commit();

        //Toast.makeText(getApplicationContext(),"서비스 시작되었습니다!"+total_minutes,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, RepeatService.class);
        intent.putExtra("total_minutes", String.valueOf(total_minutes));
        startService(intent);

        startActivity(new Intent(this, MainActivity.class));
        finish();
        return true;
    }

}
