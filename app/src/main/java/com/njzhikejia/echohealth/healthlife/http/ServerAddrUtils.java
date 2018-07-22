package com.njzhikejia.echohealth.healthlife.http;

/**
 * Created by 16222 on 2018/7/22.
 */

public class ServerAddrUtils {

    public static final String ROOT_SERVER_ADDR = "http://120.55.22.117:28001";

    public static String getLoginUrl() {
        return ROOT_SERVER_ADDR + "/user/login" +"?appid=4" +"&t=" + System.currentTimeMillis();
    }
}
