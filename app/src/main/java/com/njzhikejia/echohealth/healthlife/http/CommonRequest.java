package com.njzhikejia.echohealth.healthlife.http;

import com.google.gson.Gson;
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

   public static Request postRequest(String url, Map<String, String> params) {
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
       Map<String, String> map = new HashMap<>();
       map.put("name", name);
       String passwordSha1 = PhoneUtil.shaEncrypt(password);
       map.put("password", passwordSha1);
       return postRequest(ServerAddrUtils.getLoginUrl(), map);
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
}
