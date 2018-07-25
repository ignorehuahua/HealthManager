package com.njzhikejia.echohealth.healthlife.entity;

import org.w3c.dom.ProcessingInstruction;

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

    public class Data{

        private List<Reports> reports;

        public List<Reports> getReports() {
            return reports;
        }

        public void setReports(List<Reports> reports) {
            this.reports = reports;
        }

        public class Reports{
            private int id;
            private int user_id;
            private int type;
            private int src_id;
            private int status;
            private String create_time;
            private int result_id;
            private String result;
            private String remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getSrc_id() {
                return src_id;
            }

            public void setSrc_id(int src_id) {
                this.src_id = src_id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getResult_id() {
                return result_id;
            }

            public void setResult_id(int result_id) {
                this.result_id = result_id;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }
        }
    }
}
