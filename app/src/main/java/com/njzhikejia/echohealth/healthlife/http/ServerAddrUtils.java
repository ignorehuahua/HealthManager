package com.njzhikejia.echohealth.healthlife.http;

/**
 * Created by 16222 on 2018/7/22.
 */

public class ServerAddrUtils {

    public static final String ROOT_SERVER_ADDR = "http://120.55.22.117:28001";

    /**
     * API_HOST = http://120.55.22.117:28001
     post请求的contentType统一设置为： application/json
     post请求的body为json格式
     请求url统一携带uid参数,appid,以及t（时间戳）
     */

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
        return ROOT_SERVER_ADDR + "/user/" + uid + "?uid=" + uid + "&appid=4&t=" + System.currentTimeMillis();
    }

    /**
     * 获取最新测量数据
     * @param uid
     * @return
     */
    public static String getUserRecentMeasureData(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/measure/datas/latest?appid=4&t=" + System.currentTimeMillis() + "&uid=" + uid;
    }

    /**
     * 获取报告
     * @param uid
     * @return
     */
    public static String getReportUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/reports/" + "?uid=" + uid + "&appid=4&t=" + System.currentTimeMillis();
    }

    /**
     * 获取告警列表
     * @param uid
     * @return
     */
    public static String getWarnListUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/alarm/notices?status=0,1,2&type=1,2,3,4" ;
    }

    /**
     * 获取定位
     * @param uid
     * @return
     */
    public static String getLocationUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/locations?page=0&count=1";
    }

    /**
     * 点击获取报告详情
     * http://120.55.22.115/user/sleepReport.html?id=20&uid=1
     * @param id
     * @param uid
     * @return
     */
    public static String getReportDetailUrl(int id,int uid) {
        return ROOT_SERVER_ADDR + "/user/sleepReports?id=" + id + "&uid=" + uid;
    }

    /**
     * 获取亲友列表
     * http://120.55.22.117:28001/user/1/relatives?appid=4&t=1532096261&uid=1
     * @param uid
     * @return
     */
    public static String getRelativesUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid  + "/relatives?appid=4&t=" + System.currentTimeMillis() + "&uid=" + uid;
    }
}
