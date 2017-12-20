package com.nowui.cloud.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDateTimeString(Date dateTime) {
        if (dateTime == null) {
            return null;
        }

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormat.format(dateTime);
    }

}
