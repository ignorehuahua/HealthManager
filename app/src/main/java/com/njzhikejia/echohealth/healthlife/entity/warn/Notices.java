package com.njzhikejia.echohealth.healthlife.entity.warn;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/20.
 */

@Entity
public class Notices {

    private int id;
    private int uid;
    private int type;
    private int status;
    private String create_time;
    private String dispatch_time;
    private int dispatch_operator;
    private int screen_flag;
    private String remark;

    @Convert(converter = SrcDataConverter.class, columnType = String.class)
    private SrcData src_data;

    @Generated(hash = 632249427)
    public Notices(int id, int uid, int type, int status, String create_time,
            String dispatch_time, int dispatch_operator, int screen_flag, String remark,
            SrcData src_data) {
        this.id = id;
        this.uid = uid;
        this.type = type;
        this.status = status;
        this.create_time = create_time;
        this.dispatch_time = dispatch_time;
        this.dispatch_operator = dispatch_operator;
        this.screen_flag = screen_flag;
        this.remark = remark;
        this.src_data = src_data;
    }

    @Generated(hash = 1308447980)
    public Notices() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getDispatch_time() {
        return this.dispatch_time;
    }

    public void setDispatch_time(String dispatch_time) {
        this.dispatch_time = dispatch_time;
    }

    public int getDispatch_operator() {
        return this.dispatch_operator;
    }

    public void setDispatch_operator(int dispatch_operator) {
        this.dispatch_operator = dispatch_operator;
    }

    public int getScreen_flag() {
        return this.screen_flag;
    }

    public void setScreen_flag(int screen_flag) {
        this.screen_flag = screen_flag;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public SrcData getSrc_data() {
        return this.src_data;
    }

    public void setSrc_data(SrcData src_data) {
        this.src_data = src_data;
    }

    public static class SrcDataConverter implements PropertyConverter<SrcData, String> {

        @Override
        public SrcData convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, SrcData.class);
        }

        @Override
        public String convertToDatabaseValue(SrcData entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
