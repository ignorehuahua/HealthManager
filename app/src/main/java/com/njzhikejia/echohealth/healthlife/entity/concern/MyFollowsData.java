package com.njzhikejia.echohealth.healthlife.entity.concern;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.entity.concern.Concerns;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 16222 on 2018/9/16.
 */

public class MyFollowsData {

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

    public MyFollowsData(int ret, Data data) {
        this.ret = ret;
        this.data = data;
    }

    public static class Data{

        @Convert(columnType = String.class, converter = ConcernsConverter.class)
        List<Concerns> concerns;

        public Data(List<Concerns> concerns) {
            this.concerns = concerns;
        }

        public List<Concerns> getConcerns() {
            return concerns;
        }

        public void setConcerns(List<Concerns> concerns) {
            this.concerns = concerns;
        }

    }

    public static class ConcernsConverter implements PropertyConverter<List<Concerns>, String> {

        @Override
        public List<Concerns> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            List<String> list_str = Arrays.asList(databaseValue.split(","));
            List<Concerns> list_transport = new ArrayList<>();
            for (String s : list_str) {
                list_transport.add(new Gson().fromJson(s, Concerns.class));
            }
            return list_transport;
        }

        @Override
        public String convertToDatabaseValue(List<Concerns> entityProperty) {
            if (entityProperty == null) {
                return null;
            } else {
                StringBuilder sb = new StringBuilder();
                for (Concerns array : entityProperty) {
                    String str = new Gson().toJson(array);
                    sb.append(str);
                    sb.append(",");
                }
                return sb.toString();

            }
        }
    }
}
