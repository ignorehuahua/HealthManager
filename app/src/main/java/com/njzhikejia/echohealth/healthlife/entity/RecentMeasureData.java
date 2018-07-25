package com.njzhikejia.echohealth.healthlife.entity;

import java.util.List;

/**
 * Created by 16222 on 2018/7/24.
 */

public class RecentMeasureData {
    private int ret;
    private AllData data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public AllData getData() {
        return data;
    }

    public void setData(AllData data) {
        this.data = data;
    }

    public static class AllData{

        private List<SpecificData> data;

        public List<SpecificData> getData() {
            return data;
        }

        public void setData(List<SpecificData> data) {
            this.data = data;
        }

        public static class SpecificData{
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

            public float getValue1() {
                return value1;
            }

            public void setValue1(float value1) {
                this.value1 = value1;
            }

            public float getValue2() {
                return value2;
            }

            public void setValue2(float value2) {
                this.value2 = value2;
            }

            public float getValue3() {
                return value3;
            }

            public void setValue3(float value3) {
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
    }
}
