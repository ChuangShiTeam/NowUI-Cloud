package com.nowui.cloud.zuul.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getDateTimeString(Date dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTimeFormat.format(dateTime);
    }

}
