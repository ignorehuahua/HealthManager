package com.njzhikejia.echohealth.healthlife.util;

import android.content.Context;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.njzhikejia.echohealth.healthlife.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 16222 on 2018/6/4.
 */

public class TimeUtil {

    private static final String TAG = "TimeUtil";

    //时间选择器
    public static void showTimePickerDialog(Context context, OnTimeSelectListener onTimeSelectListener) {
        Logger.d(TAG, "showTimePickerDialog");
        TimePickerView timePickerView = new TimePickerBuilder(context, onTimeSelectListener)
                .setType(new boolean[]{true, true, true, true, true, false})
                .setCancelColor(0xFF009688)
                .setSubmitColor(0xFF009688)
                .build();
        timePickerView.show();

    }

    public static String getTime(Date date) {//可根据需要自行截取数据显示
        Logger.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

}
