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
public class Bp {

    @Convert(converter = SystolicConverter.class, columnType = String.class)
    private Systolic systolic;

    @Convert(converter = DiastolicConverter.class, columnType = String.class)
    private Diastolic diastolic;

    @Generated(hash = 543964934)
    public Bp(Systolic systolic, Diastolic diastolic) {
        this.systolic = systolic;
        this.diastolic = diastolic;
    }

    @Generated(hash = 595290385)
    public Bp() {
    }

    public Systolic getSystolic() {
        return systolic;
    }

    public void setSystolic(Systolic systolic) {
        this.systolic = systolic;
    }

    public Diastolic getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Diastolic diastolic) {
        this.diastolic = diastolic;
    }

    public static class SystolicConverter implements PropertyConverter<Systolic, String> {

        @Override
        public Systolic convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Systolic.class);
        }

        @Override
        public String convertToDatabaseValue(Systolic entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class DiastolicConverter implements PropertyConverter<Diastolic, String> {

        @Override
        public Diastolic convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Diastolic.class);
        }

        @Override
        public String convertToDatabaseValue(Diastolic entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
