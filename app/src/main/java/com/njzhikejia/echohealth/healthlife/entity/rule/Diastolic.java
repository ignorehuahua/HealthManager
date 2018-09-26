package com.njzhikejia.echohealth.healthlife.entity.rule;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/25.
 */

@Entity
public class Diastolic {
    private double min;
    private double max;

    @Generated(hash = 62245328)
    public Diastolic(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Generated(hash = 1580191131)
    public Diastolic() {
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
