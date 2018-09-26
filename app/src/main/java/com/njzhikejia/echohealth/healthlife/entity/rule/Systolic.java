package com.njzhikejia.echohealth.healthlife.entity.rule;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/25.
 */

@Entity
public class Systolic {

    private double min;
    private double max;

    @Generated(hash = 473412482)
    public Systolic(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Generated(hash = 1996388287)
    public Systolic() {
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
