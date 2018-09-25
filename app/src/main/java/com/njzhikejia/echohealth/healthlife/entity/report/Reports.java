package com.njzhikejia.echohealth.healthlife.entity.report;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/20.
 */

@Entity
public class Reports {

    private int id;
    private int uid;
    private int type;
    private int src_id;
    private int status;
    private String create_time;
    private int result_id;
    private String result;
    private String remark;

    @Generated(hash = 1468360800)
    public Reports(int id, int uid, int type, int src_id, int status,
            String create_time, int result_id, String result, String remark) {
        this.id = id;
        this.uid = uid;
        this.type = type;
        this.src_id = src_id;
        this.status = status;
        this.create_time = create_time;
        this.result_id = result_id;
        this.result = result;
        this.remark = remark;
    }
    @Generated(hash = 953319297)
    public Reports() {
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
    public void setUid(int user_id) {
        this.uid = user_id;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public int getSrc_id() {
        return this.src_id;
    }
    public void setSrc_id(int src_id) {
        this.src_id = src_id;
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
    public int getResult_id() {
        return this.result_id;
    }
    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }
    public String getResult() {
        return this.result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getRemark() {
        return this.remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
