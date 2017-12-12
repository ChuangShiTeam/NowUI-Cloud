package com.nowui.cloud.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import com.nowui.cloud.entity.BaseEntity;

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
//        Object systemCreateTime = getFieldValByName(BaseEntity.SYSTEM_CREATE_TIME, metaObject);
//        if (systemCreateTime == null) {
//            setFieldValByName(BaseEntity.SYSTEM_CREATE_TIME, new Date(), metaObject);
//        }
//        //更新时间
//        Object systemUpdateTime = getFieldValByName(BaseEntity.SYSTEM_UPDATE_TIME, metaObject);
//        if (systemUpdateTime == null) {
//            setFieldValByName(BaseEntity.SYSTEM_UPDATE_TIME, new Date(), metaObject);
//        }
//        //版本号
//        Object systemVersion = getFieldValByName(BaseEntity.SYSTEM_VERSION, metaObject);
//        if (systemVersion == null) {
//            setFieldValByName(BaseEntity.SYSTEM_VERSION, 0, metaObject);
//        }
//        //删除标志
//        Object systemStatus = getFieldValByName(BaseEntity.SYSTEM_STATUS, metaObject);
//        if (systemStatus == null) {
//            setFieldValByName(BaseEntity.SYSTEM_STATUS, false, metaObject);
//        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新时间
//        Object systemUpdateTime = getFieldValByName(BaseEntity.SYSTEM_UPDATE_TIME, metaObject);
//        if (systemUpdateTime == null) {
//            setFieldValByName(BaseEntity.SYSTEM_UPDATE_TIME, new Date(), metaObject);
//        }
    }
}