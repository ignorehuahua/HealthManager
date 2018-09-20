package com.njzhikejia.echohealth.healthlife.entity.warn;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 16222 on 2018/7/26.
 */


public class WarnNoticesData {
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

    public static class Data{
        private int total;

        @Convert(columnType = String.class, converter = NoticesConverter.class)
        private List<Notices> notices;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Notices> getNotices() {
            return notices;
        }

        public void setNotices(List<Notices> notices) {
            this.notices = notices;
        }

    }

    static class NoticesConverter implements PropertyConverter<List<Notices>, String> {

        @Override
        public List<Notices> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            List<String> list_str = Arrays.asList(databaseValue.split(","));
            List<Notices> list_transport = new ArrayList<>();
            for (String s : list_str) {
                list_transport.add(new Gson().fromJson(s, Notices.class));
            }
            return list_transport;
        }

        @Override
        public String convertToDatabaseValue(List<Notices> entityProperty) {
            if (entityProperty == null) {
                return null;
            } else {
                StringBuilder sb = new StringBuilder();
                for (Notices array : entityProperty) {
                    String str = new Gson().toJson(array);
                    sb.append(str);
                    sb.append(",");
                }
                return sb.toString();

            }
        }
    }

}
