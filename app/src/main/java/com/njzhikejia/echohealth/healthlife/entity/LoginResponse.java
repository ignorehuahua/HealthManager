package com.njzhikejia.echohealth.healthlife.entity;

/**
 * Created by 16222 on 2018/7/22.
 */

public class LoginResponse  {

    private ResponseData  data;
    private int ret;

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public class ResponseData{

        private Login login;

        public Login getLogin() {
            return login;
        }

        public void setLogin(Login login) {
            this.login = login;
        }

        public class Login{
            int uid;
            String sec_key;
            String name;

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public String getSec_key() {
                return sec_key;
            }

            public void setSec_key(String sec_key) {
                this.sec_key = sec_key;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
