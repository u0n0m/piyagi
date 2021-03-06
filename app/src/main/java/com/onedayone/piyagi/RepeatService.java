package com.onedayone.piyagi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class RepeatService extends Service  {
    boolean isStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "repeatService 시작", Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent Intent = new Intent(getApplicationContext(), RepeatAlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, Intent, 0);
        Integer second = Integer.parseInt(intent.getStringExtra("total_minutes")) * 60 * 100; // 10분의 1 간격으로 작동,  알람작동 확인이 오래걸려서
        Toast.makeText(this, second.toString(), Toast.LENGTH_SHORT).show();
        //alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + second, pIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, second, second, pIntent);
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



