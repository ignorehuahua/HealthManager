package com.njzhikejia.echohealth.healthlife.entity.measure;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class SpecificData {

    int id;
    int uid;
    int device_id;
    int src_type;
    int context;
    int type;
    String measure_time;
    String create_time;
    int operator;
    double value1;
    double value2;
    double value3;
    int status;
    int session_id;
    String remark;
    String card_uuid;
    int blood_pressure_type;

    @Generated(hash = 7712344)
    public SpecificData(int id, int uid, int device_id, int src_type, int context,
            int type, String measure_time, String create_time, int operator,
            double value1, double value2, double value3, int status, int session_id,
            String remark, String card_uuid, int blood_pressure_type) {
        this.id = id;
        this.uid = uid;
        this.device_id = device_id;
        this.src_type = src_type;
        this.context = context;
        this.type = type;
        this.measure_time = measure_time;
        this.create_time = create_time;
        this.operator = operator;
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        this.status = status;
        this.session_id = session_id;
        this.remark = remark;
        this.card_uuid = card_uuid;
        this.blood_pressure_type = blood_pressure_type;
    }

    @Generated(hash = 1766359722)
    public SpecificData() {
    }

    public int getId() {
        return id;
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

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public int getSrc_type() {
        return src_type;
    }

    public void setSrc_type(int src_type) {
        this.src_type = src_type;
    }

    public int getContext() {
        return context;
    }

    public void setContext(int context) {
        this.context = context;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMeasure_time() {
        return measure_time;
    }

    public void setMeasure_time(String measure_time) {
        this.measure_time = measure_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public int getOperator() {
        return operator;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getValue3() {
        return value3;
    }

    public void setValue3(double value3) {
        this.value3 = value3;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCard_uuid() {
        return card_uuid;
    }

    public void setCard_uuid(String card_uuid) {
        this.card_uuid = card_uuid;
    }

    public int getBlood_pressure_type() {
        return blood_pressure_type;
    }

    public void setBlood_pressure_type(int blood_pressure_type) {
        this.blood_pressure_type = blood_pressure_type;
    }
}