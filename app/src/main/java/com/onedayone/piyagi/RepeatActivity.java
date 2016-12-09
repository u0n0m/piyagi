package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
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

public class RepeatActivity extends AppCompatActivity {
    static final String FILE_NAME = "exam.txt";
    Integer repeat_hour = 1;
    Integer repeat_minute = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                save_repeat_time();
            }
        });
    }

    public boolean save_repeat_time(){
        TextView textview_repeat_hour = (TextView) findViewById(R.id.textview_repeat_hour);
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String str = textview_repeat_hour.getText().toString();
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e("File", "에러=" + e);
        }

        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String str = new String(buffer);
            Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
            toast.show();
            System.out.println("file read");
            fis.close();
        } catch (Exception e) {
            Log.e("File", "에러=" + e);
        }
        return true;
    }

}
