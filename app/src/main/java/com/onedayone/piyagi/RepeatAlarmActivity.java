package com.onedayone.piyagi;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

public class RepeatAlarmActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_repeat);
        ImageButton btn_save = (ImageButton) findViewById(R.id.btn_repeat_popup_ok);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"save 클릭됨!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

/*        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();       // 3 초후 이미지를 닫아버림
            }
        }, 4000);
*/
    }

}
