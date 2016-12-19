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
        //startActivity(new Intent(this, SplashActivity.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        repeat_listview();
//        alyac_listview();
//        hospital_listview();

        ImageButton btn_add = (ImageButton) findViewById(R.id.btn_add1);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HospitalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void repeat_listview(){
        ListView listview ;
        final RepeatListViewAdapter adapter;

        // Adapter 생성
        adapter = new RepeatListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.repeat_listview);
        listview.setAdapter(adapter);

        SharedPreferences pref_repeat = getApplicationContext().getSharedPreferences("repeatSettings", Context.MODE_PRIVATE);
        Integer order = pref_repeat.getInt("order", 0);
        //String order_string = pref_repeat.getString(order.toString(), "Blank"); //리스트뷰의 아이템 개수 가져오기
        //Toast.makeText(getApplicationContext(),"오더 번호="+order.toString(),Toast.LENGTH_SHORT).show();

        if( order <= 0 ){
            adapter.addItem("10분 간격", "안약 넣기", ContextCompat.getDrawable(this, R.drawable.circle_on));
            adapter.addItem("20분 간격", "가글 하기", ContextCompat.getDrawable(this, R.drawable.circle_off));
        }


        Integer i;
        for(i=order; i>0;i--){
            repeat_settings = pref_repeat.getString(i.toString(),"fail");
            String [] field = repeat_settings.split(":");
            if(field[3].equals("on")) {
                adapter.addItem(field[1], field[2], ContextCompat.getDrawable(this, R.drawable.circle_on));
            }
            else {
                adapter.addItem(field[1], field[2], ContextCompat.getDrawable(this, R.drawable.circle_off));
            }
        }
            //셋팅 값에서 콜론을 기준으로 1번째:번호, 2번째:반복주기, 3번째:on/off, 4번째: 설명

            Toast.makeText(getApplicationContext(),"설정값="+repeat_settings, Toast.LENGTH_SHORT).show();

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                RepeatListViewItem item = (RepeatListViewItem) parent.getItemAtPosition(position);
//                String titleStr = item.getTitle();
//                String descStr = item.getDesc();
                //item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.circle_off));
                //Toast.makeText(getApplicationContext(),"리스트뷰 버튼 클릭됨", Toast.LENGTH_SHORT).show();
            }
        });
     }

    public void alyac_listview(){
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

                String titleStr = item.getTitle();
                String descStr = item.getDesc();
                Drawable iconDrawable = item.getIcon();

                // TODO : use item data.
            }
        }) ;
    }

    public void hospital_listview(){
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

                String titleStr = item.getTitle();
                String descStr = item.getDesc();
                Drawable iconDrawable = item.getIcon();

                // TODO : use item data.
            }
        }) ;
    }

}