package com.onedayone.piyagi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.onedayone.piyagi.R;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {
    static final String FILE_NAME = "repeat_settings.value";
    Integer repeat_hour = 1;
    Integer repeat_minute = 10;
    Integer hour1;
    Integer minute1;
    Integer total_minutes;
    String on_off;
    String desc;
    byte [] buf1;
    String repeat_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        startActivity(new Intent(this, SplashActivity.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repeat();
        alyac();
        hospital();

        ImageButton btn_add = (ImageButton) findViewById(R.id.btn_add1);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RepeatActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void repeat(){
        ListView listview ;
        RepeatListViewAdapter adapter;

        // Adapter 생성
        adapter = new RepeatListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.repeat_listview);
        listview.setAdapter(adapter);

//        try {
//            FileInputStream fos = openFileInput(FILE_NAME);
//            buf1 = new byte[fos.available()];
//            fos.read(buf1);
//            repeat_settings =  buf1.toString();
//            Toast.makeText(getApplicationContext(), repeat_settings,Toast.LENGTH_SHORT).show();
//            fos.close();
//            Toast.makeText(getApplicationContext(),"파일에서 읽었습니다!",Toast.LENGTH_SHORT).show();
//        } catch (Exception e) {
//            Log.e("File", "에러=" + e);
//            Toast.makeText(getApplicationContext(),"파일 읽기 실패!",Toast.LENGTH_SHORT).show();
//        }
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("PrefName", Context.MODE_PRIVATE);
        String repeat_settings = prefs.getString("test", "");
        Toast.makeText(getApplicationContext(),repeat_settings+"파일 읽기!",Toast.LENGTH_SHORT).show();

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save), repeat_settings, "Box  Black") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save), "Circle", "Circle Black") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                RepeatListViewItem item = (RepeatListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.
            }
        }) ;
    }

    public void alyac(){
        ListView listview ;
        AlyacListViewAdapter adapter;

        // Adapter 생성
        adapter = new AlyacListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.alyac_listview);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save), "Alyac", "Box  Black") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save), "Circle", "Circle Black") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                AlyacListViewItem item = (AlyacListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.
            }
        }) ;
    }

    public void hospital(){
        ListView listview ;
        HospitalListViewAdapter adapter;

        // Adapter 생성
        adapter = new HospitalListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.hospital_listview);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save), "Hospital", "Box  Black") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save), "Circle", "Circle Black") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),  "Ind", "Ind Black") ;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                HospitalListViewItem item = (HospitalListViewItem) parent.getItemAtPosition(position) ;

                String titleStr = item.getTitle() ;
                String descStr = item.getDesc() ;
                Drawable iconDrawable = item.getIcon() ;

                // TODO : use item data.
            }
        }) ;
    }

}