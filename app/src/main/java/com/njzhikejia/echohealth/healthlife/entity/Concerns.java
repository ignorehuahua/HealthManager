package com.njzhikejia.echohealth.healthlife.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Concerns implements Parcelable{
    private int concern_id;
    private int uid;
    private String name;
    private String nickname;
    private String idcard;
    private String phone;
    private int gender;
    private String birthday;
    private String home_addr;
    private String avatar;
    private int concern_type;
    private int status;
    private String create_time;
    private String result_time;
    @Generated(hash = 1282825095)
    public Concerns(int concern_id, int uid, String name, String nickname,
            String idcard, String phone, int gender, String birthday,
            String home_addr, String avatar, int concern_type, int status,
            String create_time, String result_time) {
        this.concern_id = concern_id;
        this.uid = uid;
        this.name = name;
        this.nickname = nickname;
        this.idcard = idcard;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.home_addr = home_addr;
        this.avatar = avatar;
        this.concern_type = concern_type;
        this.status = status;
        this.create_time = create_time;
        this.result_time = result_time;
    }
    @Generated(hash = 1411771484)
    public Concerns() {
    }

    protected Concerns(Parcel in) {
        concern_id = in.readInt();
        uid = in.readInt();
        name = in.readString();
        nickname = in.readString();
        idcard = in.readString();
        phone = in.readString();
        gender = in.readInt();
        birthday = in.readString();
        home_addr = in.readString();
        avatar = in.readString();
        concern_type = in.readInt();
        status = in.readInt();
        create_time = in.readString();
        result_time = in.readString();
    }

    public static final Creator<Concerns> CREATOR = new Creator<Concerns>() {
        @Override
        public Concerns createFromParcel(Parcel in) {
            return new Concerns(in);
        }

        @Override
        public Concerns[] newArray(int size) {
            return new Concerns[size];
        }
    };

    public int getConcern_id() {
        return this.concern_id;
    }
    public void setConcern_id(int concern_id) {
        this.concern_id = concern_id;
    }
    public int getUid() {
        return this.uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getIdcard() {
        return this.idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public int getGender() {
        return this.gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getHome_addr() {
        return this.home_addr;
    }
    public void setHome_addr(String home_addr) {
        this.home_addr = home_addr;
    }
    public String getAvatar() {
        return this.avatar;
    }
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public int getConcern_type() {
        return this.concern_type;
    }
    public void setConcern_type(int concern_type) {
        this.concern_type = concern_type;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public String getResult_time() {
        return this.result_time;
    }
    public void setResult_time(String result_time) {
        this.result_time = result_time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(concern_id);
        dest.writeInt(uid);
        dest.writeString(name);
        dest.writeString(nickname);
        dest.writeString(idcard);
        dest.writeString(phone);
        dest.writeInt(gender);
        dest.writeString(birthday);
        dest.writeString(home_addr);
        dest.writeString(avatar);
        dest.writeInt(concern_type);
        dest.writeInt(status);
        dest.writeString(create_time);
        dest.writeString(result_time);
    }
}