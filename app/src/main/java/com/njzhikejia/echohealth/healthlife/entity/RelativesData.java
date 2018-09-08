package com.njzhikejia.echohealth.healthlife.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by 16222 on 2018/9/8.
 */

public class RelativesData implements Parcelable{

    private int ret;
    private Data data;

    protected RelativesData(Parcel in) {
        ret = in.readInt();
    }

    public static final Creator<RelativesData> CREATOR = new Creator<RelativesData>() {
        @Override
        public RelativesData createFromParcel(Parcel in) {
            return new RelativesData(in);
        }

        @Override
        public RelativesData[] newArray(int size) {
            return new RelativesData[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ret);
    }

    public static class Data{

        private List<Relatives> relatives;

        public List<Relatives> getRelatives() {
            return relatives;
        }

        public void setRelatives(List<Relatives> relatives) {
            this.relatives = relatives;
        }

        public static class Relatives implements Parcelable{
            private int id;
            private int uid;
            private String name;
            private int gender;
            private String callnumber;
            private String phone;
            private String relative;
            private String address;
            private int is_emergency;
            private String remark;

            public Relatives(Parcel in) {
                id = in.readInt();
                uid = in.readInt();
                name = in.readString();
                gender = in.readInt();
                callnumber = in.readString();
                phone = in.readString();
                relative = in.readString();
                address = in.readString();
                is_emergency = in.readInt();
                remark = in.readString();
            }

            public static final Creator<Relatives> CREATOR = new Creator<Relatives>() {
                @Override
                public Relatives createFromParcel(Parcel in) {
                    return new Relatives(in);
                }

                @Override
                public Relatives[] newArray(int size) {
                    return new Relatives[size];
                }
            };

            public Relatives() {

            }

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

            public String getCallnumber() {
                return callnumber;
            }

            public void setCallnumber(String callnumber) {
                this.callnumber = callnumber;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getRelative() {
                return relative;
            }

            public void setRelative(String relative) {
                this.relative = relative;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getIs_emergency() {
                return is_emergency;
            }

            public void setIs_emergency(int is_emergency) {
                this.is_emergency = is_emergency;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(id);
                dest.writeInt(uid);
                dest.writeString(name);
                dest.writeInt(gender);
                dest.writeString(callnumber);
                dest.writeString(phone);
                dest.writeString(relative);
                dest.writeString(address);
                dest.writeInt(is_emergency);
                dest.writeString(remark);
            }
        }
    }
}
