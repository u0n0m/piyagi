package com.onedayone.piyagi;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RepeatListViewAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<RepeatListViewItem> listViewItemList = new ArrayList<RepeatListViewItem>() ;

    public RepeatListViewAdapter() {

    }

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.repeat_listview_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView periodTextView = (TextView) convertView.findViewById(R.id.repeat_textview1);
        TextView descTextView = (TextView) convertView.findViewById(R.id.repeat_textview2);
        ImageButton imgbtn = (ImageButton) convertView.findViewById(R.id.repeat_imagebutton);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        final RepeatListViewItem listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        periodTextView.setText(listViewItem.getperiod());
        descTextView.setText(listViewItem.getDesc());
        imgbtn.setImageDrawable(listViewItem.getIcon());

        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //여기에 메인화며 리스트뷰에 ON버튼을 클릭하면 sharedPreference값을 off로 변경, off버튼을 클릭하면 on으로 변경
                //그리고 메인화면 리스트뷰 갱신 작업-화면에 바뀐거롤 보이도록 하려고....
//                SharedPreferences pref_repeat = context.getSharedPreferences("repeatSettings", Context.MODE_PRIVATE);
//                Integer order = pref_repeat.getInt("order", 0);
//                String repeat_settings = pref_repeat.getString(order.toString(),"fail");
                SharedPreferences pref_repeat = context.getSharedPreferences("repeatSettings", Context.MODE_PRIVATE);
                String repeat_settings = pref_repeat.getString("2", "fail");

                String [] arr = repeat_settings.split(":");
                if(arr[3].equals("on")){
                    repeat_settings = arr[0]+arr[1]+arr[2]+"off";
                }
                else if(arr[3].equals("off")){
                    repeat_settings = arr[0]+arr[1]+arr[2]+"on";
                }

                //Toast.makeText(context, "onClick: position=" + position, Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "setting(onoff)=" + arr[3], Toast.LENGTH_SHORT).show();
                System.out.println("click");
                //listViewItem.setIcon(ContextCompat.getDrawable(context, R.drawable.circle_off));
            }
        });
        return convertView;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String period, String desc, Drawable icon) {
        RepeatListViewItem item = new RepeatListViewItem();
        item.setPeriod(period);
        item.setDesc(desc);
        item.setIcon(icon);
        listViewItemList.add(item);
    }


}
