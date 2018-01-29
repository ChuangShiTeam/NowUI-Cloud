package com.nowui.cloud.exception;

/**
 * 系统异常
 * 
 * @author marcus
 *
 * 2018年1月26日
 */
public class SystemException extends RuntimeException {

    public SystemException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (message) : s;
    }

}
