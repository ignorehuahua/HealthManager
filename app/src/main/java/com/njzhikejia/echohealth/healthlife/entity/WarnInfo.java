package com.njzhikejia.echohealth.healthlife.entity;

/**
 * Created by 16222 on 2018/6/30.
 */

public class WarnInfo {

    private int warnType;
    private String warnName;
    private String warnValue;
    private int time;

    public int getWarnType() {
        return warnType;
    }

    public void setWarnType(int warnType) {
        this.warnType = warnType;
    }

    public String getWarnName() {
        return warnName;
    }

    public void setWarnName(String warnName) {
        this.warnName = warnName;
    }

    public String getWarnValue() {
        return warnValue;
    }

    public void setWarnValue(String warnValue) {
        this.warnValue = warnValue;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public WarnInfo() {

    }

    public WarnInfo(int warnType, String warnName, String warnValue, int time) {
        this.warnType = warnType;
        this.warnName = warnName;
        this.warnValue = warnValue;
        this.time = time;
    }
}
