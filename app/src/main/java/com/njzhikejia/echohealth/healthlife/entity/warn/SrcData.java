package com.njzhikejia.echohealth.healthlife.entity.warn;

import com.google.gson.Gson;
import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.converter.PropertyConverter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 16222 on 2018/9/20.
 */

@Entity
public class SrcData {

    @Convert(converter = MeasureConverter.class, columnType = String.class)
    private Measure measure;

    @Convert(converter = RegionAdaminConverter.class, columnType = String.class)
    private RegionAdamin region_adamin;

    @Generated(hash = 1237367777)
    public SrcData(Measure measure, RegionAdamin region_adamin) {
        this.measure = measure;
        this.region_adamin = region_adamin;
    }

    @Generated(hash = 578538912)
    public SrcData() {
    }

    public Measure getMeasure() {
        return this.measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public RegionAdamin getRegion_adamin() {
        return this.region_adamin;
    }

    public void setRegion_adamin(RegionAdamin region_adamin) {
        this.region_adamin = region_adamin;
    }

    public static class MeasureConverter implements PropertyConverter<Measure, String> {

        @Override
        public Measure convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Measure.class);
        }

        @Override
        public String convertToDatabaseValue(Measure entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

    public static class RegionAdaminConverter implements PropertyConverter<RegionAdamin, String> {

        @Override
        public RegionAdamin convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, RegionAdamin.class);
        }

        @Override
        public String convertToDatabaseValue(RegionAdamin entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

}
