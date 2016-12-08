package com.onedayone.piyagi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.onedayone.piyagi.R;

public class RepeatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repeat_main);

        ImageButton btn_alyac = (ImageButton) findViewById(R.id.btn_alyac);
        btn_alyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_hospital = (ImageButton) findViewById(R.id.btn_hospital);
        btn_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HospitalActivity.class);
                startActivity(intent);
            }
        });
    }
}
