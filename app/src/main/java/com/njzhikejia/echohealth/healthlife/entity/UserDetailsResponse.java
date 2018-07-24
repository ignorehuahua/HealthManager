package com.njzhikejia.echohealth.healthlife.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 16222 on 2018/7/22.
 */

public class UserDetailsResponse implements Parcelable{

    private ResponseData  data;
    private int ret;

    protected UserDetailsResponse(Parcel in) {
        ret = in.readInt();
    }

    public static final Creator<UserDetailsResponse> CREATOR = new Creator<UserDetailsResponse>() {
        @Override
        public UserDetailsResponse createFromParcel(Parcel in) {
            return new UserDetailsResponse(in);
        }

        @Override
        public UserDetailsResponse[] newArray(int size) {
            return new UserDetailsResponse[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ret);
    }

    public class ResponseData{

        private User user;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public class User{
            private int id;
            private int tenant_id;
            private int house_id;
            private String name;
            private String nickname;
            private String idcard;
            private String phone;
            private int gender;
            private String birthday;
            private String home_addr;
            private int status;
            private String avatar;
            private String create_time;
            private String remark;
            private Extend extend;

            public Extend getExtend() {
                return extend;
            }

            public void setExtend(Extend extend) {
                this.extend = extend;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTenant_id() {
                return tenant_id;
            }

            public void setTenant_id(int tenant_id) {
                this.tenant_id = tenant_id;
            }

            public int getHouse_id() {
                return house_id;
            }

            public void setHouse_id(int house_id) {
                this.house_id = house_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getHome_addr() {
                return home_addr;
            }

            public void setHome_addr(String home_addr) {
                this.home_addr = home_addr;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public class Extend{
                private int uid;
                private int gov_flag;
                private int nation;
                private int community;
                private int household;
                private int marriage;
                private int political_status;
                private int edu_level;
                private int health_state;
                private int blood_group;
                private String interests;
                private int pay_type;
                private int sport_habit;
                private int meal_habit;
                private int eat_ability;
                private int wash_ability;
                private int wear_ability;
                private int toilet_ability;
                private int move_ability;
                private int revenue_level;
                private String chronic_illness;
                private String disability;
                private String surgery_history;
                private String irritability_history;
                private String family_medical_history;
                private String height;
                private String weight;
                private String bust;
                private String waist;
                private String hip;
                private String remark;
                private String readme;
                private int constitution_type;

                public int getUid() {
                    return uid;
                }

                public void setUid(int uid) {
                    this.uid = uid;
                }

                public int getGov_flag() {
                    return gov_flag;
                }

                public void setGov_flag(int gov_flag) {
                    this.gov_flag = gov_flag;
                }

                public int getNation() {
                    return nation;
                }

                public void setNation(int nation) {
                    this.nation = nation;
                }

                public int getCommunity() {
                    return community;
                }

                public void setCommunity(int community) {
                    this.community = community;
                }

                public int getHousehold() {
                    return household;
                }

                public void setHousehold(int household) {
                    this.household = household;
                }

                public int getMarriage() {
                    return marriage;
                }

                public void setMarriage(int marriage) {
                    this.marriage = marriage;
                }

                public int getPolitical_status() {
                    return political_status;
                }

                public void setPolitical_status(int political_status) {
                    this.political_status = political_status;
                }

                public int getEdu_level() {
                    return edu_level;
                }

                public void setEdu_level(int edu_level) {
                    this.edu_level = edu_level;
                }

                public int getHealth_state() {
                    return health_state;
                }

                public void setHealth_state(int health_state) {
                    this.health_state = health_state;
                }

                public int getBlood_group() {
                    return blood_group;
                }

                public void setBlood_group(int blood_group) {
                    this.blood_group = blood_group;
                }

                public String getInterests() {
                    return interests;
                }

                public void setInterests(String interests) {
                    this.interests = interests;
                }

                public int getPay_type() {
                    return pay_type;
                }

                public void setPay_type(int pay_type) {
                    this.pay_type = pay_type;
                }

                public int getSport_habit() {
                    return sport_habit;
                }

                public void setSport_habit(int sport_habit) {
                    this.sport_habit = sport_habit;
                }

                public int getMeal_habit() {
                    return meal_habit;
                }

                public void setMeal_habit(int meal_habit) {
                    this.meal_habit = meal_habit;
                }

                public int getEat_ability() {
                    return eat_ability;
                }

                public void setEat_ability(int eat_ability) {
                    this.eat_ability = eat_ability;
                }

                public int getWash_ability() {
                    return wash_ability;
                }

                public void setWash_ability(int wash_ability) {
                    this.wash_ability = wash_ability;
                }

                public int getWear_ability() {
                    return wear_ability;
                }

                public void setWear_ability(int wear_ability) {
                    this.wear_ability = wear_ability;
                }

                public int getToilet_ability() {
                    return toilet_ability;
                }

                public void setToilet_ability(int toilet_ability) {
                    this.toilet_ability = toilet_ability;
                }

                public int getMove_ability() {
                    return move_ability;
                }

                public void setMove_ability(int move_ability) {
                    this.move_ability = move_ability;
                }

                public int getRevenue_level() {
                    return revenue_level;
                }

                public void setRevenue_level(int revenue_level) {
                    this.revenue_level = revenue_level;
                }

                public String getChronic_illness() {
                    return chronic_illness;
                }

                public void setChronic_illness(String chronic_illness) {
                    this.chronic_illness = chronic_illness;
                }

                public String getDisability() {
                    return disability;
                }

                public void setDisability(String disability) {
                    this.disability = disability;
                }

                public String getSurgery_history() {
                    return surgery_history;
                }

                public void setSurgery_history(String surgery_history) {
                    this.surgery_history = surgery_history;
                }

                public String getIrritability_history() {
                    return irritability_history;
                }

                public void setIrritability_history(String irritability_history) {
                    this.irritability_history = irritability_history;
                }

                public String getFamily_medical_history() {
                    return family_medical_history;
                }

                public void setFamily_medical_history(String family_medical_history) {
                    this.family_medical_history = family_medical_history;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWeight() {
                    return weight;
                }

                public void setWeight(String weight) {
                    this.weight = weight;
                }

                public String getBust() {
                    return bust;
                }

                public void setBust(String bust) {
                    this.bust = bust;
                }

                public String getWaist() {
                    return waist;
                }

                public void setWaist(String waist) {
                    this.waist = waist;
                }

                public String getHip() {
                    return hip;
                }

                public void setHip(String hip) {
                    this.hip = hip;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }

                public String getReadme() {
                    return readme;
                }

                public void setReadme(String readme) {
                    this.readme = readme;
                }

                public int getConstitution_type() {
                    return constitution_type;
                }

                public void setConstitution_type(int constitution_type) {
                    this.constitution_type = constitution_type;
                }
            }
        }
    }
}
