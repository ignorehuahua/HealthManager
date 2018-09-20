package com.njzhikejia.echohealth.healthlife.util;

import com.njzhikejia.echohealth.healthlife.entity.Concerns;
import com.njzhikejia.echohealth.healthlife.entity.MyFollowsData;
import com.njzhikejia.echohealth.healthlife.entity.RelativesData;

import java.text.Collator;
import java.util.Comparator;

/**
 * Created by 16222 on 2018/9/8.
 */

public class ChineseCharComp implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Collator myCollator = Collator.getInstance(java.util.Locale.CHINA);
        Concerns relative1 = (Concerns) o1;
       Concerns relative2 = (Concerns) o2;
        if (myCollator.compare(relative1.getName(), relative2.getName()) < 0)
            return -1;
        else if (myCollator.compare(relative1.getName(), relative2.getName()) > 0)
            return 1;
        else
            return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
