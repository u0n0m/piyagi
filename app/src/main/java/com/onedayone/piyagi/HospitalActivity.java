package com.onedayone.piyagi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.onedayone.piyagi.R;

public class HospitalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospital_main);

        ImageButton btn_repeat = (ImageButton) findViewById(R.id.btn_repeat3);
        btn_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RepeatActivity.class);
                startActivity(intent);
            }
        });

        ImageButton btn_alyac = (ImageButton) findViewById(R.id.btn_alyac3);
        btn_alyac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AlyacActivity.class);
                startActivity(intent);
            }
        });
    }
}
