package com.onedayone.piyagi;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
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

public class ServiceThread extends Thread{
    Handler handler;
    boolean isRun = true;

    public ServiceThread(Handler handler){
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this) {
        this.isRun = false;
        }
    }

    public void run(){
        //반복적으로 수행할 작업을 한다.
        while(isRun){
            handler.sendEmptyMessage(0);//쓰레드에 있는 핸들러에게 메세지를 보냄
            try{
                Thread.sleep(7000); //10초씩 쉰다.
            }catch (Exception e) {}
        }
    }
}