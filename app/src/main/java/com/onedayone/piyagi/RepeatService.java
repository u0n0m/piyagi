package com.onedayone.piyagi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Shader;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

public class RepeatService extends Service {
    NotificationManager Notifi_M;
    ServiceThread thread;
    Notification Notifi ;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        RepeatServiceHandler handler = new RepeatServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        thread.stopForever();
        thread = null;//쓰레기 값을 만들어서 빠르게 회수하라고 null을 넣어줌.
    }

    class RepeatServiceHandler extends Handler {
        @Override
        public void handleMessage(android.os.Message msg) {
            Intent intent = new Intent(RepeatService.this, RepeatActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(RepeatService.this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);

            Notifi = new Notification.Builder(getApplicationContext())
                    .setContentTitle("주기복용")
                    .setContentText("정해진 시간마다 복약하세요")
                    .setSmallIcon(R.drawable.piyagi1)
                    .setTicker("알림!!!")
                    .setContentIntent(pendingIntent)
                    .build();

            //소리추가
            Notifi.defaults = Notification.DEFAULT_SOUND;

            //알림 소리를 한번만 내도록
            Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;

            //확인하면 자동으로 알림이 제거 되도록
            Notifi.flags = Notification.FLAG_AUTO_CANCEL;

            Notifi_M.notify( 777 , Notifi);

            //토스트 띄우기
            //Toast.makeText(RepeatService.this, "복약하세요", Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), AlarmActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

        }
    };
}