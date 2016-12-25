package com.onedayone.piyagi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class AlyacMorningService extends Service  {
    boolean isStop = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "AlyacMorningService 시작", Toast.LENGTH_SHORT).show();
        AlarmManager alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent Intent = new Intent(getApplicationContext(), AlyacMorningAlarmActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, Intent, 0);
        String morning = intent.getStringExtra("morning");
        Toast.makeText(this, morning.toString(), Toast.LENGTH_SHORT).show();
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, second, second, pIntent);
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



