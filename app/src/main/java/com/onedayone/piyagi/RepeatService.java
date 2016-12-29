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

public class RepeatService extends Service  {
    boolean isStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent Intent = new Intent(getApplicationContext(), AlyacMorningAlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, Intent, 0);

        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("AlyacSettings", Context.MODE_PRIVATE);
        String morning_ampm_setting = pref_alyac.getString("morning_ampm", "오전오후 설정안됨");
        String morning_hour_setting = pref_alyac.getString("morning_hour", "복약시 설정안됨");
        String morning_minute_setting = pref_alyac.getString("morning_minute", "복약분 설정안됨");
        String morning_onoff_setting = pref_alyac.getString("morning_onoff", "설정상태 설정안됨");


        if(morning_onoff_setting.equals("켜짐")){

            if(morning_ampm_setting.equals("오후")){
                morning_hour_setting = morning_hour_setting + 12; //알람 매니저에 24시간 체계로 시간을 입력해주기 위해서...
                //Toast.makeText(this, "서비스시작:"+morning_ampm_setting+":"+morning_hour_setting+":"+morning_minute_setting, Toast.LENGTH_LONG).show();
            }

            //Integer hour = intent.getIntExtra("hour",0);
            //Integer minute = intent.getIntExtra("minute",0);

            Calendar calendar = Calendar.getInstance();
            //calendar.set(Calendar.YEAR, 2016);
            //calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
            //calendar.set(Calendar.DATE, 1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(morning_hour_setting));
            calendar.set(Calendar.MINUTE, Integer.parseInt(morning_minute_setting));
            calendar.set(Calendar.SECOND, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);

        }
        else if(morning_onoff_setting.equals("꺼짐")){ //나중에 구현하자
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



