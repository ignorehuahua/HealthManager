package com.njzhikejia.echohealth.healthlife.entity.warn;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.greendao.ReportsDao;

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

    @Convert(converter = LocationConverter.class, columnType = String.class)
    private Location location;

    @Convert(converter = FalldownConverter.class, columnType = String.class)
    private Falldown falldown;

    @Convert(converter = RegionAdaminConverter.class, columnType = String.class)
    private RegionAdamin region_adamin;

    @Generated(hash = 388766062)
    public SrcData(Measure measure, Location location, Falldown falldown, RegionAdamin region_adamin) {
        this.measure = measure;
        this.location = location;
        this.falldown = falldown;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Falldown getFalldown() {
        return falldown;
    }

    public void setFalldown(Falldown falldown) {
        this.falldown = falldown;
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

    public static class LocationConverter implements PropertyConverter<Location, String> {

        @Override
        public Location convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Location.class);
        }

        @Override
        public String convertToDatabaseValue(Location entityProperty) {
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

    public static class FalldownConverter implements PropertyConverter<Falldown, String> {

        @Override
        public Falldown convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Falldown.class);
        }

        @Override
        public String convertToDatabaseValue(Falldown entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }

}
