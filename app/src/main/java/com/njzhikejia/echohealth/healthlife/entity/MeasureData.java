package com.njzhikejia.echohealth.healthlife.entity;

/**
 * Created by 16222 on 2018/5/27.
 */

public class MeasureData {

    private int dataType;
    private String dataName;
    private String dataValue;
    private int healthRate;
    private String time;

    public MeasureData() {
    }

    public MeasureData(int dataType, String dataName, String dataValue, int healthRate, String time) {
        this.dataType = dataType;
        this.dataName = dataName;
        this.dataValue = dataValue;
        this.healthRate = healthRate;
        this.time = time;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public int getHealthRate() {
        return healthRate;
    }

    public void setHealthRate(int healthRate) {
        this.healthRate = healthRate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
