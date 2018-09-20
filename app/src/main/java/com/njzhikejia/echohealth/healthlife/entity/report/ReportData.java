package com.njzhikejia.echohealth.healthlife.entity.report;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 16222 on 2018/7/25.
 */

public class ReportData {
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

        @Convert(columnType = String.class, converter = ReportDataConverter.class)
        private List<Reports> reports;

        public List<Reports> getReports() {
            return reports;
        }

        public void setReports(List<Reports> reports) {
            this.reports = reports;
        }

    }

   public static class ReportDataConverter implements PropertyConverter<List<ReportData>, String> {

       @Override
       public List<ReportData> convertToEntityProperty(String databaseValue) {
           if (databaseValue == null) {
               return null;
           }
           List<String> list_str = Arrays.asList(databaseValue.split(","));
           List<ReportData> list_transport = new ArrayList<>();
           for (String s : list_str) {
               list_transport.add(new Gson().fromJson(s, ReportData.class));
           }
           return list_transport;
       }

       @Override
       public String convertToDatabaseValue(List<ReportData> entityProperty) {
           if (entityProperty == null) {
               return null;
           } else {
               StringBuilder sb = new StringBuilder();
               for (ReportData array : entityProperty) {
                   String str = new Gson().toJson(array);
                   sb.append(str);
                   sb.append(",");
               }
               return sb.toString();
           }
       }
   }
}
