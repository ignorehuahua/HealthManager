package com.njzhikejia.echohealth.healthlife.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Concerneds {

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
    @Generated(hash = 1301655202)
    public Concerneds(int concern_id, int uid, String name, String nickname,
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
    @Generated(hash = 1531998959)
    public Concerneds() {
    }
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
}