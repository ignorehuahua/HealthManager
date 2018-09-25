package com.njzhikejia.echohealth.healthlife.entity.rule;

public class WarnRule {

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
        private Rules rules;

        public Rules getRules() {
            return rules;
        }

        public void setRules(Rules rules) {
            this.rules = rules;
        }

        public class Rules{
            private int id;
            private int uid;
            private int status;
            private int operator_id;
            private String data;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getOperator_id() {
                return operator_id;
            }

            public void setOperator_id(int operator_id) {
                this.operator_id = operator_id;
            }

            public String getData() {
                return data;
            }

            public void setData(String data) {
                this.data = data;
            }
        }
    }
}