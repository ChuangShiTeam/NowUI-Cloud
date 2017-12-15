package com.nowui.cloud.handler;

import java.util.Date;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;

/**
 * 自定义填充公共字段(创建时间、更新时间、删除标志、版本号等字段)
 * @author marcus
 *
 */
public class CommonMetaObjectHandler extends MetaObjectHandler {

    /**
     * 创建时间、更新时间、版本号、删除标志等字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //创建时间
//        Object systemCreateTime = getFieldValByName("systemCreateTime", metaObject);
//        if (systemCreateTime == null) {
//            setFieldValByName("systemCreateTime", new Date(), metaObject);
//        }
        System.out.println("++++++");
        System.out.println(JSON.toJSONString(metaObject));
        System.out.println("++++++");
        setFieldValByName("systemCreateTime", new Date(), metaObject);

        //更新时间
//        Object systemUpdateTime = getFieldValByName("systemUpdateTime", metaObject);
//        if (systemUpdateTime == null) {
//            setFieldValByName("systemUpdateTime", new Date(), metaObject);
//        }
        setFieldValByName("systemUpdateTime", new Date(), metaObject);

        //版本号
//        Object systemVersion = getFieldValByName("systemVersion", metaObject);
//        if (systemVersion == null) {
//            setFieldValByName("systemVersion", 0, metaObject);
//        }
        setFieldValByName("systemVersion", 0, metaObject);

        //删除标志
//        Object systemStatus = getFieldValByName("systemStatus", metaObject);
//        if (systemStatus == null) {
//            setFieldValByName("systemStatus", false, metaObject);
//        }
        setFieldValByName("systemStatus", true, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时间
//        Object systemUpdateTime = getFieldValByName("systemUpdateTime", metaObject);
//        if (systemUpdateTime == null) {
//            setFieldValByName("systemUpdateTime", new Date(), metaObject);
//        }
        setFieldValByName("systemUpdateTime", new Date(), metaObject);
    }
}