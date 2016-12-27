package com.onedayone.piyagi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Calendar;

public class HospitalService extends Service  {
    boolean isStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent Intent = new Intent(getApplicationContext(), HospitalAlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, Intent, 0);

        SharedPreferences pref_hospital = getApplicationContext().getSharedPreferences("HospitalSettings", Context.MODE_PRIVATE);
        String hospital_year_setting = pref_hospital.getString("hospital_year", "년 설정안됨");
        String hospital_month_setting = pref_hospital.getString("hospital_month", "월 설정안됨");
        String hospital_day_setting = pref_hospital.getString("hospital_day", "일 설정안됨");
        String hospital_ampm_setting = pref_hospital.getString("hospital_ampm", "오전오후 설정안됨");
        String hospital_hour_setting = pref_hospital.getString("hospital_hour", "복약시 설정안됨");
        String hospital_minute_setting = pref_hospital.getString("hospital_minute", "복약분 설정안됨");
        String hospital_onoff_setting = pref_hospital.getString("hospital_onoff", "설정상태 설정안됨");

        if(hospital_onoff_setting.equals("켜짐")){
            if(hospital_ampm_setting.equals("오후")){
                hospital_hour_setting = hospital_hour_setting + 12; //알람 매니저에 24시간 체계로 시간을 입력해주기 위해서...
                Toast.makeText(this, "서비스시작:"+hospital_ampm_setting+":"+hospital_hour_setting+":"+hospital_minute_setting, Toast.LENGTH_LONG).show();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(hospital_year_setting));
            calendar.set(Calendar.MONTH, Integer.parseInt(hospital_month_setting));
            calendar.set(Calendar.DATE, Integer.parseInt(hospital_day_setting));
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hospital_hour_setting));
            calendar.set(Calendar.MINUTE, Integer.parseInt(hospital_minute_setting));
            calendar.set(Calendar.SECOND, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);

        }
        else if(hospital_onoff_setting.equals("꺼짐")){ //나중에 구현하자
/*
            AlarmManager mAlarmMgr = null;
            PendingIntent mAlarmIntent = null;
            if(mAlarmIntent != null) {
                mAlarmMgr = (AlarmManager) Context.getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(Context.getApplicationContext(), xxxx.class);
                mAlarmIntent = PendingIntent.getBroadcast(mContext.getApplicationContext(), FFFF_START_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                mAlarmMgr.cancel(mAlarmIntent);
                mAlarmIntent.cancel();
                mAlarmMgr = null;
                mAlarmIntent = null;
*/
/*                Intent intentlocal = new Intent(this,AlyacMorningService.class);
                PendingIntent pilocal = PendingIntent.getBroadcast(SampledateActivity.this,
                        0, intentlocal, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.cancel(pilocal);
                pilocal.cancel();
            }*/
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}



