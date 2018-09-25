package com.njzhikejia.echohealth.healthlife.entity.rule;

/**
 * Created by 16222 on 2018/9/25.
 */

public class Body_temperature {

    private float normal_min;
    private float normal_max;
    private float low_min;
    private float low_max;
    private float medium_min;
    private float medium_max;
    private float high_min;
    private float high_max;
    private float super_high_min;

    public float getNormal_min() {
        return normal_min;
    }

    public void setNormal_min(float normal_min) {
        this.normal_min = normal_min;
    }

    public float getNormal_max() {
        return normal_max;
    }

    public void setNormal_max(float normal_max) {
        this.normal_max = normal_max;
    }

    public float getLow_min() {
        return low_min;
    }

    public void setLow_min(float low_min) {
        this.low_min = low_min;
    }

    public float getLow_max() {
        return low_max;
    }

    public void setLow_max(float low_max) {
        this.low_max = low_max;
    }

    public float getMedium_min() {
        return medium_min;
    }

    public void setMedium_min(float medium_min) {
        this.medium_min = medium_min;
    }

    public float getMedium_max() {
        return medium_max;
    }

    public void setMedium_max(float medium_max) {
        this.medium_max = medium_max;
    }

    public float getHigh_min() {
        return high_min;
    }

    public void setHigh_min(float high_min) {
        this.high_min = high_min;
    }

    public float getHigh_max() {
        return high_max;
    }

    public void setHigh_max(float high_max) {
        this.high_max = high_max;
    }

    public float getSuper_high_min() {
        return super_high_min;
    }

    public void setSuper_high_min(float super_high_min) {
        this.super_high_min = super_high_min;
    }
}
