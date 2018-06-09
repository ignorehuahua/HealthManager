package com.njzhikejia.echohealth.healthlife.http;

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

   public static Request postRequest(String url, Map<String, String> params) {
       FormBody.Builder builder = new FormBody.Builder();
       if (params != null) {
           for (Map.Entry<String, String> entity : params.entrySet()) {
               builder.add(entity.getKey(), entity.getValue());
           }
       }
       Request request = new Request.Builder()
               .url(url)
               .post(builder.build())
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
}
