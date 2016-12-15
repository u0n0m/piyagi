package com.onedayone.piyagi;

import android.graphics.drawable.Drawable;

public class RepeatListViewItem {
    private String periodStr ;
    private String descStr ;
    private Drawable iconDrawable ;

    public void setPeriod(String period) {
        periodStr = period;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }

    public String getperiod() {
        return this.periodStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public Drawable getIcon() {
        return this.iconDrawable ;
    }

}