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
    public static String getReportUrl(int uid, int page, int count) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/reports/" + "?uid=" + uid + "&appid=4&page=" + page + "&count=" + count + "&t=" +System.currentTimeMillis();
    }

    /**
     * http://120.55.22.117:28001/user/2/alarm/rules
     * 获取告警规则
     * @param userId
     * @return
     */
    public static String getWarnRuleUrl(int userId) {
        return ROOT_SERVER_ADDR + "/user/" + userId + "/alarm/rules";
    }


    /**
     * 获取告警列表
     * @param uid
     * @return
     */
    public static String getWarnListUrl(int uid, int page, int count) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/alarm/notices?status=0,1,2&type=1,2,3,4&appid=4&page=" + page + "&count=" +count
                + "&t=" + System.currentTimeMillis();
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

    /**
     * 获取我的关注
     *  http get 'http://120.55.22.117:28001/user/1/concerns?appid=4&t=12313123123&uid=1'
     * @param uid
     * @return
     */
    public static String getMyFollowsUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/concerns?appid=4&t=" + System.currentTimeMillis() + "&uid=" + uid;
    }

    /**
     * 获取“关注我的”用户列表
     http get http://120.55.22.117:28001/user/1/concerneds?appid=4&t=12313123123&uid=1'
     * @param uid
     * @return
     */
    public static String getFollowMesUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/concerneds?appid=4&t=" + System.currentTimeMillis() + "&uid=" + uid;
    }

    /**
     * 用户上传设备信息
     * post 'http://120.55.22.117:28001/user/1/devices?appid=4&t=1532007925'
     * @param uid
     * @return
     */
    public static String getUpdateDeviceTokenUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/devices?appid=4&t=" + System.currentTimeMillis();
    }

    /**
     * 用户发起关注申请
     * http://120.55.22.117:28001/user/5/concerns/1?appid=4&t=1532007925
     * @param userId
     * @param concernedUid
     * @return
     */
    public static String getStartConcrensUrl(int userId, int concernedUid) {
        return ROOT_SERVER_ADDR + "/user/" + userId + "/concerns/" + concernedUid + "?appid=4&t=" + System.currentTimeMillis();
    }


    /**
     * 处理关注申请
     * http://120.55.22.117:28001/user/1/handle/concerns/9?appid=4&t=1532007925
     * @param userId
     * @param concernId
     * @return
     */
    public static String getHandleConcrensUrl(int userId, int concernId) {
        return ROOT_SERVER_ADDR + "/user/" + userId + "/handle/concerns/" + concernId + "?appid=4&t=" + System.currentTimeMillis();
    }

    /**
     * 重置密码
     * http://120.55.22.117:28001/user/1/password?appid=4&t=1532007925
     * @param uid
     * @return
     */
    public static String getResetPwdUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/password?appid=4&t=" + System.currentTimeMillis();
    }

    /**
     * 用户意见反馈
     * http://120.55.22.117:28001/user/1/feebback?appid=4&t=1532007925
     * @param uid
     * @return
     */
    public static String getFeedbackUrl(int uid) {
        return ROOT_SERVER_ADDR + "/user/" + uid + "/feedback?appid=4&t="  + System.currentTimeMillis();
    }
}
