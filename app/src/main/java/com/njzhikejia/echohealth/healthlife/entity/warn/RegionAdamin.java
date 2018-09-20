package com.njzhikejia.echohealth.healthlife.entity.warn;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/20.
 */

@Entity
public class RegionAdamin {

    private int id;
    private int role;
    private String name;
    private int gender;
    private String phone1;
    private int status;
    @Generated(hash = 292534725)
    public RegionAdamin(int id, int role, String name, int gender, String phone1,
            int status) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.gender = gender;
        this.phone1 = phone1;
        this.status = status;
    }
    @Generated(hash = 215750223)
    public RegionAdamin() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getRole() {
        return this.role;
    }
    public void setRole(int role) {
        this.role = role;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getGender() {
        return this.gender;
    }
    public void setGender(int gender) {
        this.gender = gender;
    }
    public String getPhone1() {
        return this.phone1;
    }
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
