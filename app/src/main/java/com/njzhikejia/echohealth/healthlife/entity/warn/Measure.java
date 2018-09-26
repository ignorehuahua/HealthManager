package com.njzhikejia.echohealth.healthlife.entity.warn;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/20.
 */

@Entity
public class Measure {

    private int id;
    private int uid;
    private int device_id;
    private int src_type;
    private int src_id;
    private int context;
    private int type;
    private String measure_time;
    private String create_time;
    private int operator;
    private double value1;
    private double value2;
    private double value3;
    private int status;
    private int session_id;
    private String remark;

    @Generated(hash = 530850514)
    public Measure(int id, int uid, int device_id, int src_type, int src_id,
            int context, int type, String measure_time, String create_time,
            int operator, double value1, double value2, double value3, int status,
            int session_id, String remark) {
        this.id = id;
        this.uid = uid;
        this.device_id = device_id;
        this.src_type = src_type;
        this.src_id = src_id;
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
    }

    @Generated(hash = 1840334633)
    public Measure() {
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

    public int getSrc_id() {
        return src_id;
    }

    public void setSrc_id(int src_id) {
        this.src_id = src_id;
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
}
