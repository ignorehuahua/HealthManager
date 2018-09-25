package com.njzhikejia.echohealth.healthlife.entity.rule;

/**
 * Created by 16222 on 2018/9/25.
 */

public class RuleResult {
    private Pulse pulse;
    private Bp bp;
    private Spo2 spo2;
    private Blood_sugar blood_sugar;
    private Body_temperature body_temperature;

    public Spo2 getSpo2() {
        return spo2;
    }

    public void setSpo2(Spo2 spo2) {
        this.spo2 = spo2;
    }

    public Blood_sugar getBlood_sugar() {
        return blood_sugar;
    }

    public void setBlood_sugar(Blood_sugar blood_sugar) {
        this.blood_sugar = blood_sugar;
    }

    public Body_temperature getBody_temperature() {
        return body_temperature;
    }

    public void setBody_temperature(Body_temperature body_temperature) {
        this.body_temperature = body_temperature;
    }

    public Bp getBp() {
        return bp;
    }

    public void setBp(Bp bp) {
        this.bp = bp;
    }

    public Pulse getPulse() {
        return pulse;
    }

    public void setPulse(Pulse pulse) {
        this.pulse = pulse;
    }
}
