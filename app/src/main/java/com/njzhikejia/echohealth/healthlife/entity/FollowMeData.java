package com.njzhikejia.echohealth.healthlife.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 16222 on 2018/9/16.
 */

public class FollowMeData {

    private int ret;
    private Data data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public FollowMeData(int ret, Data data) {
        this.ret = ret;
        this.data = data;
    }

    public static class Data{

        @Convert(columnType = String.class, converter = ConcernedsConverter.class)
        List<Concerneds> concerneds;

        public Data(List<Concerneds> concerneds) {
            this.concerneds = concerneds;
        }

        public List<Concerneds> getConcerns() {
            return concerneds;
        }

        public void setConcerns(List<Concerneds> concerns) {
            this.concerneds = concerns;
        }

    }

    public static class ConcernedsConverter implements PropertyConverter<List<Concerneds>, String> {

        @Override
        public List<Concerneds> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            List<String> list_str = Arrays.asList(databaseValue.split(","));
            List<Concerneds> list_transport = new ArrayList<>();
            for (String s : list_str) {
                list_transport.add(new Gson().fromJson(s, Concerneds.class));
            }
            return list_transport;
        }

        @Override
        public String convertToDatabaseValue(List<Concerneds> entityProperty) {
            if (entityProperty == null) {
                return null;
            } else {
                StringBuilder sb = new StringBuilder();
                for (Concerneds array : entityProperty) {
                    String str = new Gson().toJson(array);
                    sb.append(str);
                    sb.append(",");
                }
                return sb.toString();

            }
        }
    }
}
