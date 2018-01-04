package com.nowui.cloud.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * @author ZhongYongQiang
 */
public class Util {

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static boolean isNullOrEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }

        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }

        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public static String readData(HttpServletRequest request) {
        BufferedReader br = null;

        try {
            StringBuilder result = new StringBuilder();

            String line;
            for (br = request.getReader(); (line = br.readLine()) != null; result.append(line)) {
                if (result.length() > 0) {
                    result.append("\n");
                }
            }

            line = result.toString();
            return line;
        } catch (IOException var12) {
            throw new RuntimeException(var12);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException var11) {

                }
            }

        }
    }
    
    public static String createPath(String ...params) {
        StringBuilder builder = new StringBuilder();
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i ++) {
                builder.append(params[i]);
                builder.append("/");
            }  
        }
        return builder.toString();
    }

}
