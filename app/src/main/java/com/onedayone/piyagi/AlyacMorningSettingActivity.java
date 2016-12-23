package com.onedayone.piyagi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AlyacMorningSettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alyac_morning_setting);
        //setContentView(R.layout.activity_alyac_morning_alarm);

        Button test = (Button) findViewById(R.id.alyac_morning_btn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacMorningAlarmActivity.class);
                startActivity(intent);
                //finish();
            }
        });
    }
}
