package com.njzhikejia.echohealth.healthlife.entity.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/19.
 */

@Entity
public class User implements Parcelable{

    @Id
    private Long id;
    private int tenant_id;
    private int house_id;
    private String name;
    private String nickname;
    private String idcard;
    private String phone;
    private int gender;
    private String birthday;
    private String home_addr;
    private int status;
    private String avatar;
    private String create_time;
    private String remark;
    @Convert(converter = ExtendConverter.class, columnType = String.class)
    private Extend extend;



    protected User(Parcel in) {
        id = in.readLong();
        tenant_id = in.readInt();
        house_id = in.readInt();
        name = in.readString();
        nickname = in.readString();
        idcard = in.readString();
        phone = in.readString();
        gender = in.readInt();
        birthday = in.readString();
        home_addr = in.readString();
        status = in.readInt();
        avatar = in.readString();
        create_time = in.readString();
        remark = in.readString();
    }

    @Generated(hash = 209762436)
    public User(Long id, int tenant_id, int house_id, String name, String nickname,
            String idcard, String phone, int gender, String birthday, String home_addr,
            int status, String avatar, String create_time, String remark, Extend extend) {
        this.id = id;
        this.tenant_id = tenant_id;
        this.house_id = house_id;
        this.name = name;
        this.nickname = nickname;
        this.idcard = idcard;
        this.phone = phone;
        this.gender = gender;
        this.birthday = birthday;
        this.home_addr = home_addr;
        this.status = status;
        this.avatar = avatar;
        this.create_time = create_time;
        this.remark = remark;
        this.extend = extend;
    }

    @Generated(hash = 586692638)
    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTenant_id() {
        return this.tenant_id;
    }

    public void setTenant_id(int tenant_id) {
        this.tenant_id = tenant_id;
    }

    public int getHouse_id() {
        return this.house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
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

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCreate_time() {
        return this.create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Extend getExtend() {
        return this.extend;
    }

    public void setExtend(Extend extend) {
        this.extend = extend;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(tenant_id);
        dest.writeInt(house_id);
        dest.writeString(name);
        dest.writeString(nickname);
        dest.writeString(idcard);
        dest.writeString(phone);
        dest.writeInt(gender);
        dest.writeString(birthday);
        dest.writeString(home_addr);
        dest.writeInt(status);
        dest.writeString(avatar);
        dest.writeString(create_time);
        dest.writeString(remark);
    }

    public static class ExtendConverter implements PropertyConverter<Extend, String> {

        @Override
        public Extend convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Extend.class);

        }

        @Override
        public String convertToDatabaseValue(Extend entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
