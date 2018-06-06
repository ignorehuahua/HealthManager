package com.njzhikejia.echohealth.healthlife.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 16222 on 2018/6/6.
 */

public class PhoneUtil {
    /**
     * 验证手机号码
     * @param phoneNumber 手机号码
     * @return boolean
     */
    public static boolean checkPhoneNumber(String phoneNumber){
        Pattern pattern=Pattern.compile("^1[0-9]{10}$");
        Matcher matcher=pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
