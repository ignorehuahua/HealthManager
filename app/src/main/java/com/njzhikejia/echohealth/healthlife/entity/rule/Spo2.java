package com.njzhikejia.echohealth.healthlife.entity.rule;

/**
 * Created by 16222 on 2018/9/25.
 */

public class Spo2 {
    private float normal_min;
    private float normal_max;
    private float light_min;
    private float light_max;
    private float medium_min;
    private float medium_max;
    private float serious_min;
    private float serious_max;

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

    public float getLight_min() {
        return light_min;
    }

    public void setLight_min(float light_min) {
        this.light_min = light_min;
    }

    public float getLight_max() {
        return light_max;
    }

    public void setLight_max(float light_max) {
        this.light_max = light_max;
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

    public float getSerious_min() {
        return serious_min;
    }

    public void setSerious_min(float serious_min) {
        this.serious_min = serious_min;
    }

    public float getSerious_max() {
        return serious_max;
    }

    public void setSerious_max(float serious_max) {
        this.serious_max = serious_max;
    }
}
