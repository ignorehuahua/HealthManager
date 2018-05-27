package com.njzhikejia.echohealth.healthlife.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 16222 on 2018/5/26.
 */

public class Member{
    private Bitmap bitmap;
    private String name;
    private String recentTime;


    public Member() {
    }

    public Member(Bitmap bitmap, String name, String recentTime) {
        this.bitmap = bitmap;
        this.name = name;
        this.recentTime = recentTime;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(String recentTime) {
        this.recentTime = recentTime;
    }
}
