package com.onedayone.piyagi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
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

import java.io.IOException;

import static com.onedayone.piyagi.R.id.alyac_morning_btn;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String year_setting, month_setting, day_setting, ampm_setting, hour_setting, minute_setting, onoff_setting, description_setting, period_setting;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaPlayer mPlayer;
        mPlayer = new MediaPlayer();
        mPlayer.create(this, R.raw.baby_chick_sounds);        // res/raw 폴더안에 desperado.mp3 파일을 삽입했고 그파일을 참조
        mPlayer.setLooping(true);
        mPlayer.setAudioStreamType(0);
        try{
            mPlayer.prepare();
        }catch(Exception e){

        }
        mPlayer.start();        // 재생 시작



//        MediaPlayer mPlayer = new MediaPlayer();         // 객체생성
// TYPE_RINGTONE 을 하면 현재 설정되어 있는 밸소리를 가져온다.
// 만약 알람음을 가져오고 싶다면 TYPE_ALARM 을 이용하면 된다
/*        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        try {
            // 이렇게 URI 객체를 그대로 삽입해줘야한다.
            //인터넷에서 url.toString() 으로 하는것이 보이는데 해보니까 안된다 -_-;
            mPlayer.setDataSource(this, alert);


            // 출력방식(재생시 사용할 방식)을 설정한다. STREAM_RING 은 외장 스피커로,
            // STREAM_VOICE_CALL 은 전화-수신 스피커를 사용한다.
            mPlayer.setAudioStreamType(AudioManager.STREAM_RING);

            mPlayer.setLooping(true);  // 반복여부 지정
            mPlayer.prepare();    // 실행전 준비
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();   // 실행 시작*/

        //알약 아침 복용 메뉴 표시
        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        ampm_setting = pref_alyac.getString("morning_ampm", "오전");
        hour_setting = pref_alyac.getString("morning_hour", "07");
        minute_setting = pref_alyac.getString("morning_minute", "30");
        onoff_setting = pref_alyac.getString("morning_onoff", "꺼짐");
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

        //병원 예약 메뉴 표시
        SharedPreferences pref_hospital = getApplicationContext().getSharedPreferences("HospitalSettings", Context.MODE_PRIVATE);
        pref_hospital = getApplicationContext().getSharedPreferences("HospitalSettings", Context.MODE_PRIVATE);
        year_setting = pref_hospital.getString("hospital_year", "오전");
        month_setting = pref_hospital.getString("hospital_month", "07");
        day_setting = pref_hospital.getString("hospital_dfay", "30");
        ampm_setting = pref_hospital.getString("hospital_ampm", "오전");
        hour_setting = pref_hospital.getString("hospital_hour", "07");
        minute_setting = pref_hospital.getString("hospital_minute", "30");
        onoff_setting = pref_hospital.getString("hospital_onoff", "꺼짐");
        description_setting = pref_hospital.getString("hospital_description", "OO병원 OO과");
        //Toast.makeText(getApplicationContext(), "read2 hospital_onoff)=" + hospital_onoff_setting, Toast.LENGTH_LONG).show();
        String hospital_settings1 = year_setting + "/" + month_setting + "/" + day_setting + " " + ampm_setting + " " + hour_setting + ":" + minute_setting;
        String hospital_settings2 = description_setting;
        tv = (TextView) findViewById(R.id.hospital_textview1);
        tv.setText(hospital_settings1);
        tv = (TextView) findViewById(R.id.hospital_textview2);
        tv.setText(hospital_settings2);
        btn = (Button) findViewById(R.id.hospital_btn);
        btn.setText(onoff_setting);

        //반복 복용 메뉴 표시
        SharedPreferences pref_repeat = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE);
        pref_repeat = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE);
        ampm_setting = pref_repeat.getString("repeat_ampm", "오전");
        hour_setting = pref_repeat.getString("repeat_hour", "07");
        minute_setting = pref_repeat.getString("repeat_minute", "30");
        onoff_setting = pref_repeat.getString("repeat_onoff", "꺼짐");
        period_setting = pref_repeat.getString("repeat_period", "4");
        String repeat_settings = ampm_setting + " " + hour_setting + ":" + minute_setting + "부터\n  " + period_setting + "시간 간격";
        tv = (TextView) findViewById(R.id.repeat_textview1);
        tv.setText(repeat_settings);
        btn = (Button) findViewById(R.id.repeat_btn);
        btn.setText(onoff_setting);


        //////////////////////////////////////////////////////////////////////////////////

        // 아침약 복용 예약 설정 버튼 기능 구현
        RelativeLayout morning = (RelativeLayout) findViewById(R.id.main_alyac_morning_menu);
        morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacMorningSettingActivity.class);
                startActivity(intent);
                //System.gc();
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

        // 병원 내원 예약 설정 버튼 기능 구현
        RelativeLayout hospital = (RelativeLayout) findViewById(R.id.main_hospital_menu);
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HospitalSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        Button hospital_onoff = (Button) findViewById(R.id.hospital_btn);
        hospital_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button hospital_onoff = (Button) findViewById(R.id.hospital_btn);
                SharedPreferences pref_hospital = getApplicationContext().getSharedPreferences("HospitalSettings", Context.MODE_PRIVATE);
                String hospital_onoff_setting = pref_hospital.getString("hospital_onoff", "복약분 설정안됨");
                if(hospital_onoff_setting.equals("켜짐") ) {
                    hospital_onoff.setText("꺼짐");
                    pref_hospital = getApplicationContext().getSharedPreferences("HospitalSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_hospital.edit(); //sharedPreference 내용 수정
                    editor.putString("hospital_onoff","꺼짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
                if(hospital_onoff_setting.equals("꺼짐") ) {
                    hospital_onoff.setText("켜짐");
                    pref_hospital = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_hospital.edit(); //sharedPreference 내용 수정
                    editor.putString("hospital_onoff","켜짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
            }
        });

        // 반복 복용 예약 설정 버튼 기능 구현
        RelativeLayout repeat = (RelativeLayout) findViewById(R.id.main_repeat_menu);
        repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RepeatSettingActivity.class);
                startActivity(intent);
                System.gc();
                //finish();
            }
        });
        Button repeat_onoff = (Button) findViewById(R.id.repeat_btn);
        repeat_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button repeat_onoff = (Button) findViewById(R.id.repeat_btn);
                SharedPreferences pref_repeat = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE);
                String repeat_onoff_setting = pref_repeat.getString("repeat_onoff", "복약분 설정안됨");
                if(repeat_onoff_setting.equals("켜짐") ) {
                    repeat_onoff.setText("꺼짐");
                    pref_repeat = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_repeat.edit(); //sharedPreference 내용 수정
                    editor.putString("repeat_onoff","꺼짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
                if(repeat_onoff_setting.equals("꺼짐") ) {
                    repeat_onoff.setText("켜짐");
                    pref_repeat = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE); // 설정 정보 읽어오기
                    SharedPreferences.Editor editor = pref_repeat.edit(); //sharedPreference 내용 수정
                    editor.putString("repeat_onoff","켜짐"); //아침약 알람 설정 저장
                    editor.commit(); // 설정 적용
                }
            }
        });
    }

    @Override
    public void onPause() {
        System.gc();
        super.onPause();
        finish();
        //finish();
    }

    @Override
    protected void onStop() {
        System.gc();
        super.onStop();
        finish();
    }

    @Override
    protected void onDestroy() {
        System.gc();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        System.gc();
        super.onBackPressed();
        finish();
    }

}