package com.njzhikejia.echohealth.healthlife.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserDetailsResponse implements Parcelable{

    private ResponseData  data;
    private int ret;

    protected UserDetailsResponse(Parcel in) {
        ret = in.readInt();
    }

    public UserDetailsResponse() {
    }

    public static final Creator<UserDetailsResponse> CREATOR = new Creator<UserDetailsResponse>() {
        @Override
        public UserDetailsResponse createFromParcel(Parcel in) {
            return new UserDetailsResponse(in);
        }

        @Override
        public UserDetailsResponse[] newArray(int size) {
            return new UserDetailsResponse[size];
        }
    };

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ret);
    }

    public static class ResponseData{

        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }
}
