package com.njzhikejia.echohealth.healthlife.entity.rule;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/25.
 */

@Entity
public class Vein_whole_blood {
    private double min;
    private double max = 1.2f;

    @Generated(hash = 1063642234)
    public Vein_whole_blood(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Generated(hash = 1887018238)
    public Vein_whole_blood() {
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
