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
        Toast.makeText(this, "반복서비스시작:", Toast.LENGTH_LONG).show();
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent Intent = new Intent(getApplicationContext(), RepeatAlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, Intent, 0);

        SharedPreferences pref_alyac = getApplicationContext().getSharedPreferences("RepeatSettings", Context.MODE_PRIVATE);
        String repeat_ampm_setting = pref_alyac.getString("repeat_ampm", "오전오후 설정안됨");
        String repeat_hour_setting = pref_alyac.getString("repeat_hour", "복약시 설정안됨");
        String repeat_minute_setting = pref_alyac.getString("repeat_minute", "복약분 설정안됨");
        String repeat_onoff_setting = pref_alyac.getString("repeat_onoff", "설정상태 설정안됨");
        String repeat_period_setting = pref_alyac.getString("repeat_onoff", "설정상태 설정안됨");

        if(repeat_onoff_setting.equals("켜짐")){

            if(repeat_ampm_setting.equals("오후")){
                Integer temp = Integer.parseInt(repeat_hour_setting) + 12; //알람 매니저에 24시간 체계로 시간을 입력해주기 위해서...
                repeat_hour_setting = temp.toString();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(repeat_hour_setting));
            calendar.set(Calendar.MINUTE, Integer.parseInt(repeat_minute_setting));
            calendar.set(Calendar.SECOND, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
        }
        else if(repeat_onoff_setting.equals("꺼짐")){ //나중에 구현하자
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
/*                Intent intentlocal = new Intent(this,AlyacRepeatService.class);
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



