package com.njzhikejia.echohealth.healthlife.entity;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 16222 on 2018/5/26.
 */

public class Member implements Parcelable{
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

    protected Member(Parcel in) {
        bitmap = in.readParcelable(Bitmap.class.getClassLoader());
        name = in.readString();
        recentTime = in.readString();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(bitmap, flags);
        dest.writeString(name);
        dest.writeString(recentTime);
    }
}
