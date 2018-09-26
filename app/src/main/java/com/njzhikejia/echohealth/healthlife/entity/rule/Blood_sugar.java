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
public class Blood_sugar {

    @Convert(converter = Vein_whole_bloodConverter.class, columnType = String.class)
    private Vein_whole_blood vein_whole_blood;

    @Generated(hash = 1686291726)
    public Blood_sugar(Vein_whole_blood vein_whole_blood) {
        this.vein_whole_blood = vein_whole_blood;
    }

    @Generated(hash = 1769599565)
    public Blood_sugar() {
    }

    public Vein_whole_blood getVein_whole_blood() {
        return vein_whole_blood;
    }

    public void setVein_whole_blood(Vein_whole_blood vein_whole_blood) {
        this.vein_whole_blood = vein_whole_blood;
    }

    public static class Vein_whole_bloodConverter implements PropertyConverter<Vein_whole_blood, String> {

        @Override
        public Vein_whole_blood convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            return new Gson().fromJson(databaseValue, Vein_whole_blood.class);
        }

        @Override
        public String convertToDatabaseValue(Vein_whole_blood entityProperty) {
            if (entityProperty == null) {
                return null;
            }
            return new Gson().toJson(entityProperty);
        }
    }
}
