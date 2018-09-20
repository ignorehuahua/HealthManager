package com.njzhikejia.echohealth.healthlife.entity;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 16222 on 2018/7/24.
 */

public class RecentMeasureData {
    private int ret;
    private AllData data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public AllData getData() {
        return data;
    }

    public void setData(AllData data) {
        this.data = data;
    }

    public static class AllData{

        @Convert(columnType = String.class, converter = SpecificDataConverter.class)
        private List<SpecificData> data;

        public List<SpecificData> getData() {
            return data;
        }

        public void setData(List<SpecificData> data) {
            this.data = data;
        }
    }

    public static class SpecificDataConverter implements PropertyConverter<List<SpecificData>, String> {

        @Override
        public List<SpecificData> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            List<String> list_str = Arrays.asList(databaseValue.split(","));
            List<SpecificData> list_transport = new ArrayList<>();
            for (String s : list_str) {
                list_transport.add(new Gson().fromJson(s, SpecificData.class));
            }
            return list_transport;
        }

        @Override
        public String convertToDatabaseValue(List<SpecificData> entityProperty) {
            if (entityProperty == null) {
                return null;
            } else {
                StringBuilder sb = new StringBuilder();
                for (SpecificData array : entityProperty) {
                    String str = new Gson().toJson(array);
                    sb.append(str);
                    sb.append(",");
                }
                return sb.toString();

            }
        }
    }
}
