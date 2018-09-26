package com.njzhikejia.echohealth.healthlife.entity.rule;

/**
 * Created by 16222 on 2018/9/25.
 */

public class Spo2 {
    private double normal_min;
    private double normal_max;
    private double light_min;
    private double light_max;
    private double medium_min;
    private double medium_max;
    private double serious_min;
    private double serious_max;

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

    public double getLight_min() {
        return light_min;
    }

    public void setLight_min(double light_min) {
        this.light_min = light_min;
    }

    public double getLight_max() {
        return light_max;
    }

    public void setLight_max(double light_max) {
        this.light_max = light_max;
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

    public double getSerious_min() {
        return serious_min;
    }

    public void setSerious_min(double serious_min) {
        this.serious_min = serious_min;
    }

    public double getSerious_max() {
        return serious_max;
    }

    public void setSerious_max(double serious_max) {
        this.serious_max = serious_max;
    }
}
