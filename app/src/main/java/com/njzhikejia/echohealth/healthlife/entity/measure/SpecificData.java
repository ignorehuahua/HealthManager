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
    float value1;
    float value2;
    float value3;
    int status;
    int session_id;
    String remark;
    String card_uuid;
    int blood_pressure_type;
    @Generated(hash = 853370812)
    public SpecificData(int id, int uid, int device_id, int src_type, int context,
            int type, String measure_time, String create_time, int operator,
            float value1, float value2, float value3, int status, int session_id,
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
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUid() {
        return this.uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getDevice_id() {
        return this.device_id;
    }
    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }
    public int getSrc_type() {
        return this.src_type;
    }
    public void setSrc_type(int src_type) {
        this.src_type = src_type;
    }
    public int getContext() {
        return this.context;
    }
    public void setContext(int context) {
        this.context = context;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getMeasure_time() {
        return this.measure_time;
    }
    public void setMeasure_time(String measure_time) {
        this.measure_time = measure_time;
    }
    public String getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
    public int getOperator() {
        return this.operator;
    }
    public void setOperator(int operator) {
        this.operator = operator;
    }
    public float getValue1() {
        return this.value1;
    }
    public void setValue1(float value1) {
        this.value1 = value1;
    }
    public float getValue2() {
        return this.value2;
    }
    public void setValue2(float value2) {
        this.value2 = value2;
    }
    public float getValue3() {
        return this.value3;
    }
    public void setValue3(float value3) {
        this.value3 = value3;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getSession_id() {
        return this.session_id;
    }
    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getCard_uuid() {
        return this.card_uuid;
    }
    public void setCard_uuid(String card_uuid) {
        this.card_uuid = card_uuid;
    }
    public int getBlood_pressure_type() {
        return this.blood_pressure_type;
    }
    public void setBlood_pressure_type(int blood_pressure_type) {
        this.blood_pressure_type = blood_pressure_type;
    }

}