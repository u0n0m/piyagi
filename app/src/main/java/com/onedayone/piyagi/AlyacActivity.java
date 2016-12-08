package com.onedayone.piyagi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.onedayone.piyagi.R;

public class AlyacActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alyac_main);

        ImageButton btn_repeat = (ImageButton) findViewById(R.id.btn_repeat2);
        btn_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RepeatActivity.class);
                startActivity(intent);
            }
        });
        ImageButton btn_hospital = (ImageButton) findViewById(R.id.btn_hospital2);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HospitalActivity.class);
                startActivity(intent);
            }
        });
    }
}
