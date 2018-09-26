package com.njzhikejia.echohealth.healthlife.entity.rule;

/**
 * Created by 16222 on 2018/9/25.
 */

public class Body_temperature {

    private double normal_min;
    private double normal_max;
    private double low_min;
    private double low_max;
    private double medium_min;
    private double medium_max;
    private double high_min;
    private double high_max;
    private double super_high_min;

    public double getNormal_min() {
        return normal_min;
    }

    public void setNormal_min(double normal_min) {
        this.normal_min = normal_min;
    }

    public double getNormal_max() {
        return normal_max;
    }

    public void setNormal_max(double normal_max) {
        this.normal_max = normal_max;
    }

    public double getLow_min() {
        return low_min;
    }

    public void setLow_min(double low_min) {
        this.low_min = low_min;
    }

    public double getLow_max() {
        return low_max;
    }

    public void setLow_max(double low_max) {
        this.low_max = low_max;
    }

    public double getMedium_min() {
        return medium_min;
    }

    public void setMedium_min(double medium_min) {
        this.medium_min = medium_min;
    }

    public double getMedium_max() {
        return medium_max;
    }

    public void setMedium_max(double medium_max) {
        this.medium_max = medium_max;
    }

    public double getHigh_min() {
        return high_min;
    }

    public void setHigh_min(double high_min) {
        this.high_min = high_min;
    }

    public double getHigh_max() {
        return high_max;
    }

    public void setHigh_max(double high_max) {
        this.high_max = high_max;
    }

    public double getSuper_high_min() {
        return super_high_min;
    }

    public void setSuper_high_min(double super_high_min) {
        this.super_high_min = super_high_min;
    }
}
