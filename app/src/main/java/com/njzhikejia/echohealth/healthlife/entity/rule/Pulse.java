package com.njzhikejia.echohealth.healthlife.entity.rule;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Pulse {

   private double min;
   private double max;

@Generated(hash = 1692210732)
public Pulse(double min, double max) {
    this.min = min;
    this.max = max;
}

@Generated(hash = 1184536889)
public Pulse() {
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