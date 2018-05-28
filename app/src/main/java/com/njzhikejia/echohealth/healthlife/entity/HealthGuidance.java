package com.njzhikejia.echohealth.healthlife.entity;

/**
 * Created by 16222 on 2018/5/28.
 */

public class HealthGuidance {

    private String healthGuideName;
    private String healthGuideTime;

    public HealthGuidance() {
    }

    public HealthGuidance(String healthGuideName, String healthGuideTime) {
        this.healthGuideName = healthGuideName;
        this.healthGuideTime = healthGuideTime;
    }

    public String getHealthGuideName() {
        return healthGuideName;
    }

    public void setHealthGuideName(String healthGuideName) {
        this.healthGuideName = healthGuideName;
    }

    public String getHealthGuideTime() {
        return healthGuideTime;
    }

    public void setHealthGuideTime(String healthGuideTime) {
        this.healthGuideTime = healthGuideTime;
    }
}
