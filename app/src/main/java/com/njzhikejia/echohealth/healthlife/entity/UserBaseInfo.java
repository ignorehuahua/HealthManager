package com.njzhikejia.echohealth.healthlife.entity;

public class UserBaseInfo {

    private String label;
    private String value;

    public UserBaseInfo(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public UserBaseInfo() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}