package com.njzhikejia.echohealth.healthlife.widget.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.njzhikejia.echohealth.healthlife.R;

/**
 * Created by 16222 on 2018/6/30.
 */

public class ViewUtil {

    public static View getView(Context context,int resId) {
        View v = LayoutInflater.from(context).inflate(
                R.layout.layout_banner, null);
        ImageView img = (ImageView) v.findViewById(R.id.iv_banner);
        img.setImageResource(resId);
        return v;
    }
}
