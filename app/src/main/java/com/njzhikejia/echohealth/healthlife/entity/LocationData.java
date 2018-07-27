package com.njzhikejia.echohealth.healthlife.entity;

import java.util.List;

/**
 * Created by 16222 on 2018/7/27.
 */

public class LocationData {

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
        private List<Locations> locations;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Locations> getLocations() {
            return locations;
        }

        public void setLocations(List locations) {
            this.locations = locations;
        }

        public class Locations{
            private int id;
            private String username;
            private int device_id;
            private float longitude;
            private float latitude;
            private float altitude;
            private String measure_time;
            private String create_time;
            private int status;
            private String city;
            private String address;
            private int type;
            private String remark;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public int getDevice_id() {
                return device_id;
            }

            public void setDevice_id(int device_id) {
                this.device_id = device_id;
            }

            public float getLongitude() {
                return longitude;
            }

            public void setLongitude(float longitude) {
                this.longitude = longitude;
            }

            public float getLatitude() {
                return latitude;
            }

            public void setLatitude(float latitude) {
                this.latitude = latitude;
            }

            public float getAltitude() {
                return altitude;
            }

            public void setAltitude(float altitude) {
                this.altitude = altitude;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
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
