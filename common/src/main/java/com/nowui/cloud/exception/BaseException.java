package com.nowui.cloud.exception;

/**
 * 自定义异常
 * 
 * @author ZhongYongQiang
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (message) : s;
    }

}
