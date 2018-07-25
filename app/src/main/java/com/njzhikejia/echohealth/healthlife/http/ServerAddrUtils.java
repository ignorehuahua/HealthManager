package com.njzhikejia.echohealth.healthlife.http;

/**
 * Created by 16222 on 2018/7/22.
 */

public class ServerAddrUtils {

    public static final String ROOT_SERVER_ADDR = "http://120.55.22.117:28001";


    /**
     *
     * @return 登录url
     */
    public static String getLoginUrl() {
        return ROOT_SERVER_ADDR + "/user/login" + "?appid=4" + "&t=" + System.currentTimeMillis();
    }

    /**
     * 获取用户详情
     * @param uid
     * @return
     */
    public static String getUserDetailsUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/1?uid=" + uid + "&appid=4&t=" + System.currentTimeMillis();
    }

    /**
     * 获取最新测量数据
     * @param uid
     * @return
     */
    public static String getUserRecentMeasureData(int uid) {
        return ROOT_SERVER_ADDR + "/user/1/measure/datas/latest?appid=4&t=" + System.currentTimeMillis() + "&uid=" + uid;
    }
}
