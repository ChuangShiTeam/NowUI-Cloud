package com.nowui.cloud.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableName;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;

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
     * 生成随机数字
     * 
     * @param length 随机数字长度
     * @return 
     */
    public static String getRandomNumber(int length) {
        StringBuilder result = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
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
    
    /**
     * 获取请求客户端的IP地址
     * 
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 实体对象列表映射实体对象字段列表
     * 
     * @param beanList 实体对象列表
     * @param field 实体对象字段
     * @return List<String> 实体对象字段列表
     */
    public static List<String> beanToField(List<? extends BaseEntity> beanList, String field) {
        if (Util.isNullOrEmpty(beanList)) {
            return null;
        }
        return beanList.stream().map(bean -> bean.getString(field)).collect(Collectors.toList());
    }
    
    /**
     * 实体对象列表映射实体对象字段列表json字符串
     * 
     * @param beanList 实体对象列表
     * @param field 实体对象字段
     * @return String 字段列表json字符串
     */
    public static String beanToFieldString(List<? extends BaseEntity> beanList, String field) {
        if (Util.isNullOrEmpty(beanList)) {
            return null;
        }
        List<String> list = beanList.stream().map(bean -> bean.getString(field)).collect(Collectors.toList());
        return JSONArray.toJSONString(list);
    }
    
    /**
     * 实体对象列表关联字段映射实体字段对象
     * @param beanList 实体对象列表
     * @param beanCloumn 实体对象匹配字段
     * @param fieldBeanList 实体对象映射实体字段列表
     * @param fieldCloumns 实体对象映射实体字段列表字段集合
     * @return List<T> 实体对象列表
     */
    public static <T extends BaseEntity> List<T> beanAddField(List<T> beanList, String beanCloumn, List<? extends BaseEntity> fieldBeanList, String ...fieldCloumns) {
        if (Util.isNullOrEmpty(beanList)) {
            return null;
        }
        if (Util.isNullOrEmpty(fieldBeanList)) {
            return beanList;
        }
        for (BaseEntity bean : beanList) {
            Optional<? extends BaseEntity> fieldBeanOption = fieldBeanList.stream().filter(fieldBean -> bean.get(beanCloumn).equals(fieldBean.get(fieldBean.getTableId()))).findFirst();
            for (String fieldCloumn : fieldCloumns) {
                bean.put(fieldCloumn, fieldBeanOption.isPresent() ? fieldBeanOption.get().get(fieldCloumn) : null);
            }
        }
        return beanList;
    }
    
    public static String getTableName(Class<? extends BaseEntity> clazz) {
        TableName table = clazz.getAnnotation(TableName.class);
        if (table != null) {
            return table.value();
        }
        return null;
    }
    
}
