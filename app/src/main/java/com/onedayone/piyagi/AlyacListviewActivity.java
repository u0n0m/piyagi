package com.onedayone.piyagi;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class AlyacListviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        ListView listview ;
        AlyacListViewAdapter adapter;

        // Adapter 생성
        adapter = new AlyacListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.alyac_listview);
        listview.setAdapter(adapter);

        // 첫 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),
                "Box", "Account Box Black 36dp") ;
        // 두 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),
                "Circle", "Account Circle Black 36dp") ;
        // 세 번째 아이템 추가.
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.btn_save),
                "Ind", "Assignment Ind Black 36dp") ;

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
}
