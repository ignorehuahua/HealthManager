package com.njzhikejia.echohealth.healthlife.entity.rule;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/25.
 */

@Entity
public class RuleResult {

    private int uid;

    @Convert(converter = PulseConverter.class, columnType = String.class)
    private Pulse pulse;

    @Convert(converter = BpConverter.class, columnType = String.class)
    private Bp bp;

    @Convert(converter = Spo2Converter.class, columnType = String.class)
    private Spo2 spo2;

    @Convert(converter = Blood_sugarConverter.class, columnType = String.class)
    private Blood_sugar blood_sugar;

    @Convert(converter = Body_temperatureConverter.class, columnType = String.class)
    private Body_temperature body_temperature;

    @Generated(hash = 2035011276)
    public RuleResult(int uid, Pulse pulse, Bp bp, Spo2 spo2, Blood_sugar blood_sugar,
            Body_temperature body_temperature) {
        this.uid = uid;
        this.pulse = pulse;
        this.bp = bp;
        this.spo2 = spo2;
        this.blood_sugar = blood_sugar;
        this.body_temperature = body_temperature;
    }

    @Generated(hash = 304839718)
    public RuleResult() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    public static class PulseConverter implements PropertyConverter<Pulse, String> {

        @Override
        public Pulse convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Pulse.class);
        }

        @Override
        public String convertToDatabaseValue(Pulse entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class BpConverter implements PropertyConverter<Bp, String> {

        @Override
        public Bp convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Bp.class);
        }

        @Override
        public String convertToDatabaseValue(Bp entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class Spo2Converter implements PropertyConverter<Spo2, String> {

        @Override
        public Spo2 convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Spo2.class);
        }

        @Override
        public String convertToDatabaseValue(Spo2 entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class Blood_sugarConverter implements PropertyConverter<Blood_sugar, String> {

        @Override
        public Blood_sugar convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Blood_sugar.class);
        }

        @Override
        public String convertToDatabaseValue(Blood_sugar entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class Body_temperatureConverter implements PropertyConverter<Body_temperature, String> {

        @Override
        public Body_temperature convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Body_temperature.class);
        }

        @Override
        public String convertToDatabaseValue(Body_temperature entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
