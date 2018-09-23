package com.njzhikejia.echohealth.healthlife.util;

/**
 * Created by 16222 on 2018/5/27.
 */

public class ConstantValues {

    public static final int BLOOD_PRESSURE = 1; // 血压

    public static final int DIASTOLIC_PRESSURE = 2; // 舒张压

    public static final int SYSTOLIC_PRESSURE = 3; // 收缩压

    public static final String KEY_MEMBER_INFO = "key_member_info";

    public static final String KEY_TO_PAGE = "key_to_page";

    public static final int PAGE_OF_DATA = 0;

    public static final int PAGE_OF_GUIDANCE = 1;

    public static final int PAGE_OF_WARN = 2;

    public static final int PAGE_OF_LOCATE = 3;

    public static final int MSG_REFRESH_TIME_OUT = 100;

    public static final int REFRESH_TIME_OUT = 10 * 1000;

    // 从相册选取
    public static final int REQUEST_CODE_OF_GRLLERY = 200;

    // 拍照
    public static final int REQUEST_CODE_OF_CAMERA = 201;

    public static final String MULTI_USER_MODE = "multi_user";

    public static final String SINGLE_USER_MODE = "sigle_user";

    public static final String KEY_REPORT_DETAIL_ID = "key_report_detail_id";

    public static final String ID_FOR_HEALTH_LIFE = "healthlife";

    public static final String SCAN_RESULT = "scan_result";

    public static final String ACTION_EXIT_LOGIN = "com.healthlife.ACTION_EXIT_LOGIN";

    public static final String ACTION_LOAD_USER_DETAILS = "con.healthlife.ACTION_LOAD_USER_DETAILS";

    public static final String ACTION_CONCERN_REQUEST_RECEIVED = "com.healthlife.ACTION_CONCERN_REQUEST_RECEIVED";

    public static final String ACTION_CONCERN_REQUEST_FINISHED = "com.healthlife.ACTION_CONCERN_REQUEST_FINISHED";

    public static final String ACTION_CONCERN_REQUEST_ACCEPTED = "com.healthlife.ACTION_CONCERN_REQUEST_ACCEPTED";

    public static final String KEY_USER_DETAILS = "key_user_details";


    /**
     * 好友申请状态
     * 0：申请处理中；1：通过；10为拒绝
     */
    public static final int STATUS_APPLY = 0;

    public static final int STATUS_DONE = 1;

    public static final int STATUS_REFUSE = 10;

    public static final String KEY_FOLLOW_ME_USER = "key_follow_me_user";

    public static final String KEY_MY_FOLLOW_USER = "key_my_follow_user";

    public static final int WAY_OF_QR_CODE = 1;

}
