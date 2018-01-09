package com.nowui.cloud.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.nowui.cloud.constant.Constant;

/**
 * @author ZhongYongQiang
 */
public class Util {

    private static Pattern emailPattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private static Pattern mobilePattern = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");

    /**
     * 生成32位的随机数， 通用唯一识别码 用于数据库主键
     * 
     * @return
     */
    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 验证NULL或空
     * 
     * @param obj
     * @return
     */
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

    /**
     * 读取HttpServletRequest请求数据
     * 
     * @param request
     * @return
     */
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

    /**
     * 拼接URI路径
     * 
     * @param params
     * @return
     */
    public static String createPath(String... params) {
        StringBuilder builder = new StringBuilder();
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                builder.append(params[i]);
                builder.append("/");
            }
        }
        return builder.toString();
    }

    /**
     * 验证email
     * 
     * @param str
     * @return
     */
    public static boolean isEmail(String str) {
        Matcher matcher = emailPattern.matcher(str);

        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 验证手机号码（13、14、15、17、18、19开头长度为11的手机号码）
     * 
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        Matcher matcher = mobilePattern.matcher(str);

        if (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     * 验证日期
     * 
     * @param str
     * @return
     */
    public static boolean isDate(String str) {
        String regex = "^((((1[6-9]|[2-9]//d)//d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]//d|3[01]))|(((1[6-9]|[2-9]//d)//d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]//d|30))|(((1[6-9]|[2-9]//d)//d{2})-0?2-(0?[1-9]|1//d|2[0-8]))|(((1[6-9]|[2-9]//d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?//d):[0-5]?//d:[0-5]?//d$";
        return match(regex, str);
    }

    /**
     * 验证数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证整型
     * 
     * @param str
     * @return
     */
    public static boolean isIntNumber(String str) {
        String regex = "^//+?[1-9][0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证小数
     * 
     * @param str
     * @return
     */
    public static boolean isDecimal(String str) {
        String regex = "^[0-9]+(.[0-9]{2})?$";
        return match(regex, str);
    }

    /**
     * 正则匹配
     * 
     * @param regex
     * @param string
     * @return
     */
    public static boolean match(String regex, String string) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

    /**
     * 密码加密
     * 
     * @param user_password
     * @return
     */
    public static String generatePassword(String userPassword) {
        if (isNullOrEmpty(userPassword)) {
            return "";
        }

        return EncryptUtil.sha512(Constant.PRIVATE_KEY + userPassword);
    }

    /**
     * 验证类里面是否存在这个属性
     * 
     * @param clazz
     * @param fieldName
     * @return
     */
    public static boolean existsField(Class clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName) != null;
        } catch (Exception e) {

        }
        
        if (clazz != Object.class) {
            return existsField(clazz.getSuperclass(), fieldName);
        }
        return false;
    }
    
    public static String repalceLast(String source, String target, String replacement) {
        int lastIndex = source.lastIndexOf(target);
        if (lastIndex > 0) {
            source = source.substring(0, lastIndex) + replacement;
        }
        return source;
    }
    
}
