package com.njzhikejia.echohealth.healthlife.entity;

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

    public class Data{
        private int total;
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

        public class Notices{
            private int id;
            private int type;
            private int status;
            private String create_time;
            private String dispatch_time;
            private int dispatch_operator;
            private int screen_flag;
            private Remark remark;
            private SrcData src_data;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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

            public String getDispatch_time() {
                return dispatch_time;
            }

            public void setDispatch_time(String dispatch_time) {
                this.dispatch_time = dispatch_time;
            }

            public int getDispatch_operator() {
                return dispatch_operator;
            }

            public void setDispatch_operator(int dispatch_operator) {
                this.dispatch_operator = dispatch_operator;
            }

            public int getScreen_flag() {
                return screen_flag;
            }

            public void setScreen_flag(int screen_flag) {
                this.screen_flag = screen_flag;
            }

            public Remark getRemark() {
                return remark;
            }

            public void setRemark(Remark remark) {
                this.remark = remark;
            }

            public SrcData getSrc_data() {
                return src_data;
            }

            public void setSrc_data(SrcData src_data) {
                this.src_data = src_data;
            }

            public class SrcData{
                private Measure measure;
                private RegionAdamin region_adamin;

                public Measure getMeasure() {
                    return measure;
                }

                public void setMeasure(Measure measure) {
                    this.measure = measure;
                }

                public RegionAdamin getRegion_adamin() {
                    return region_adamin;
                }

                public void setRegion_adamin(RegionAdamin region_adamin) {
                    this.region_adamin = region_adamin;
                }

                public class Measure{
                    private int id;
                    private int uid;
                    private int device_id;
                    private int src_type;
                    private int src_id;
                    private int context;
                    private int type;
                    private String measure_time;
                    private String create_time;
                    private int operator;
                    private float value1;
                    private float value2;
                    private float value3;
                    private int status;
                    private int session_id;
                    private String remark;

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

                    public int getDevice_id() {
                        return device_id;
                    }

                    public void setDevice_id(int device_id) {
                        this.device_id = device_id;
                    }

                    public int getSrc_type() {
                        return src_type;
                    }

                    public void setSrc_type(int src_type) {
                        this.src_type = src_type;
                    }

                    public int getSrc_id() {
                        return src_id;
                    }

                    public void setSrc_id(int src_id) {
                        this.src_id = src_id;
                    }

                    public int getContext() {
                        return context;
                    }

                    public void setContext(int context) {
                        this.context = context;
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public String getMeasure_time() {
                        return measure_time;
                    }

                    public void setMeasure_time(String measure_time) {
                        this.measure_time = measure_time;
                    }

                    public String getCreate_time() {
                        return create_time;
                    }

                    public void setCreate_time(String create_time) {
                        this.create_time = create_time;
                    }

                    public int getOperator() {
                        return operator;
                    }

                    public void setOperator(int operator) {
                        this.operator = operator;
                    }

                    public float getValue1() {
                        return value1;
                    }

                    public void setValue1(float value1) {
                        this.value1 = value1;
                    }

                    public float getValue2() {
                        return value2;
                    }

                    public void setValue2(float value2) {
                        this.value2 = value2;
                    }

                    public float getValue3() {
                        return value3;
                    }

                    public void setValue3(float value3) {
                        this.value3 = value3;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getSession_id() {
                        return session_id;
                    }

                    public void setSession_id(int session_id) {
                        this.session_id = session_id;
                    }

                    public String getRemark() {
                        return remark;
                    }

                    public void setRemark(String remark) {
                        this.remark = remark;
                    }
                }
            }

            public class Remark{
                private String notice_desc;

                public String getNotice_desc() {
                    return notice_desc;
                }

                public void setNotice_desc(String notice_desc) {
                    this.notice_desc = notice_desc;
                }
            }

            public class RegionAdamin{
                private int id;
                private int role;
                private String name;
                private int gender;
                private String phone1;
                private int status;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getGender() {
                    return gender;
                }

                public void setGender(int gender) {
                    this.gender = gender;
                }

                public String getPhone1() {
                    return phone1;
                }

                public void setPhone1(String phone1) {
                    this.phone1 = phone1;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }
            }
        }
    }
}
