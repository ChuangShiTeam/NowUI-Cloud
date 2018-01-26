package com.nowui.cloud.exception;

/**
 * 业务异常
 * 
 * @author marcus
 *
 * 2018年1月26日
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
    
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (message) : s;
    }

}
