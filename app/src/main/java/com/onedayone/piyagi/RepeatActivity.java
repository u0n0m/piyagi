package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onedayone.piyagi.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RepeatActivity extends AppCompatActivity {
    static final String FILE_NAME = "exam.txt";

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

        ImageButton btn_save = (ImageButton) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), HospitalActivity.class);
//                startActivity(intent);
                save_repeat_time();
            }
        });
    }

    public boolean save_repeat_time(){
        TextView repeat_hour = (TextView) findViewById(R.id.repeat_hour);
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            String str = repeat_hour.getText().toString();
            fos.write(str.getBytes());
            fos.close();
        } catch (Exception e) {
            Log.e("File", "에러=" + e);
        }

        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            String str = new String(buffer);
            //edit2.setText(str);
            Toast toast = Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT);
            toast.show();
            fis.close();
        } catch (Exception e) {
            Log.e("File", "에러=" + e);
        }
        return true;
    }

}
