package com.njzhikejia.echohealth.healthlife.http;

import android.support.design.widget.TabLayout;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.njzhikejia.echohealth.healthlife.util.Logger;
import com.njzhikejia.echohealth.healthlife.util.PhoneUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
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

   public static Request getRequest(String url,Map<String, String> params){
       StringBuilder urlBuilder = new StringBuilder(url).append("?");
       if (params != null) {
           for (Map.Entry<String, String> entity : params.entrySet()) {
               urlBuilder.append(entity.getKey()).append("=").append(entity.getValue()).append("&");
           }
       }
       String realUrl = urlBuilder.substring(0, urlBuilder.length() - 1);
        Request request = new Request.Builder()
                .url(realUrl)
                .get()
                .build();
        return request;
   }

   public static Request postLoginRequest(String name, String password) {
       Map<String, String> map = new HashMap<>();
       map.put("name", name);
       String passwordSha1 = PhoneUtil.shaEncrypt(password);
       map.put("password", passwordSha1);
       return postRequest(ServerAddrUtils.getLoginUrl(), map);
   }
}
