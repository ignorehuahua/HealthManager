package com.njzhikejia.echohealth.healthlife.http;

import com.google.gson.Gson;
import com.njzhikejia.echohealth.healthlife.MainActivity;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PhoneUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by 16222 on 2018/6/9.
 */

public class CommonRequest {

    private static final String TAG = "CommonRequest";

   public static Request postRequest(String url, Map<String, Object> params) {
       Gson gson = new Gson();
       String jsonStr = gson.toJson(params);
       Logger.d(TAG, "postRequest jsonStr = "+jsonStr);
       MediaType JSON = MediaType.parse("application/json; charset=utf-8");
       RequestBody body = RequestBody.create(JSON, jsonStr);
       Request request = new Request.Builder()
               .url(url)
               .post(body)
               .build();
       return request;
   }

   public static Request getRequest(String url){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        return request;
   }

    /**
     * 登录
     * @param name
     * @param password
     * @return
     */
   public static Request postLoginRequest(String name, String password) {
       Map<String, Object> map = new HashMap<>();
       map.put("name", name);
       String passwordSha1 = PhoneUtil.shaEncrypt(password);
       map.put("password", passwordSha1);
       return postRequest(ServerAddrUtils.getLoginUrl(), map);
   }

    /**
     * 用户上传设备信息
     * @param uid
     * @param device_token
     * @param sys_type
     * @return
     */
    public static Request postDeviceInfoRequest(int uid,String device_token, int sys_type) {
        Map<String, Object> map = new HashMap<>();
        map.put("device_token", device_token);
        map.put("sys_type", sys_type);
        return postRequest(ServerAddrUtils.getUpdateDeviceTokenUrl(uid), map);
    }

    /**
     * 用户发起关注申请
     * @param userId
     * @param concernedUid
     * @param concern_type
     * @return
     */
    public static Request postStartConcernRequest(int userId, int concernedUid, int concern_type) {
        Map<String, Object> map = new HashMap<>();
        map.put("concern_type", concern_type);
        return postRequest(ServerAddrUtils.getStartConcrensUrl(userId, concernedUid), map);
    }

    /**
     * 处理关注申请
     * @param userId
     * @param concernId
     * @param status
     * @return
     */
    public static Request postHandleConcernRequest(int userId, int concernId, int status) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        return postRequest(ServerAddrUtils.getHandleConcrensUrl(userId, concernId), map);
    }

    public static Request postResetPwdRequest(int uid, String old_password, String new_password) {
        Map<String, Object> map = new HashMap<>();
        String oldPasswordSha1 = PhoneUtil.shaEncrypt(old_password);
        String newPasswordSha1 = PhoneUtil.shaEncrypt(new_password);
        map.put("old_password", oldPasswordSha1);
        map.put("new_password", newPasswordSha1);
        return postRequest(ServerAddrUtils.getResetPwdUrl(uid), map);
    }

    public static Request postFeedbackRequest(int uid, String content, String img1, String img2, String img3, int type, int contact_type, String contact_info) {
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("img1", img1);
        map.put("img2", img2);
        map.put("img3", img3);
        map.put("type", type);
        map.put("contact_type", contact_type);
        map.put("contact_info", contact_info);
        return postRequest(ServerAddrUtils.getFeedbackUrl(uid), map);
    }

    /**
     * 获取用户详情
     * @param uid
     * @return
     */
   public static Request getUserDetailsRequest(int uid) {
       return getRequest(ServerAddrUtils.getUserDetailsUrl(uid));
   }

    /**
     * 获取最新测量数据
     * @param uid
     * @return
     */
   public static Request getUserRecentMeasureData(int uid) {
       return getRequest(ServerAddrUtils.getUserRecentMeasureData(uid));
   }

    /**
     * 获取报告
     * @param uid
     * @return
     */
   public static Request getUserReports(int uid) {
       return getRequest(ServerAddrUtils.getReportUrl(uid));
   }

    /**
     * 获取告警列表
     * @param uid
     * @return
     */
   public static Request getUserWarnInfo(int uid) {
       return getRequest(ServerAddrUtils.getWarnListUrl(uid));
   }

   public static Request getLocation(int uid) {
       return getRequest(ServerAddrUtils.getLocationUrl(uid));
   }

   public static Request getRealtivesList(int uid) {
       return getRequest(ServerAddrUtils.getRelativesUrl(uid));
   }

   public static Request getMyFollows(int uid) {
       return getRequest(ServerAddrUtils.getMyFollowsUrl(uid));
   }

   public static Request getFollowMes(int uid) {
       return getRequest(ServerAddrUtils.getFollowMesUrl(uid));
   }
}
