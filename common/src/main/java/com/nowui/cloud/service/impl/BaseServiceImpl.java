package com.nowui.cloud.service.impl;

import java.util.Date;
import java.util.List;

import com.nowui.cloud.util.Util;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.mapper.BaseMapper;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author ZhongYongQiang
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> {

    @Autowired
    protected M mapper;

    @Autowired
    private T entity;

    @Autowired
    private RedisTemplate<String, Object> redis;

    public T find(String id) {
        T baseEntity = (T) redis.opsForValue().get(entity.getTableName());

        if (baseEntity == null) {
            baseEntity = mapper.selectById(id);

            if (baseEntity != null) {
                redis.opsForValue().set(entity.getTableName(), baseEntity);
            }
        }

        return baseEntity;
    }

    public T find(String id, Boolean systemStatus) {
        List<T> list = mapper.selectList(
                new EntityWrapper<T>()
                        .eq(entity.getTableId(), id)
                        .eq(BaseEntity.SYSTEM_STATUS, systemStatus)
        );
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    public Boolean save(T baseEntity, String systemCreateUserId) {
        baseEntity.put(entity.getTableId(), Util.getRandomUUID());
        baseEntity.setSystemCreateUserId(systemCreateUserId);
        baseEntity.setSystemCreateTime(new Date());
        baseEntity.setSystemUpdateUserId(systemCreateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(0);
        baseEntity.setSystemStatus(true);

        Boolean success = mapper.insert(baseEntity) != 0;
        return success;
    }

    public Boolean update(T baseEntity, String id, String systemUpdateUserId, Integer systemVersion) {
        baseEntity.setSystemUpdateUserId(systemUpdateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(systemVersion + 1);

        Boolean success = mapper.update(
                baseEntity,
                new EntityWrapper<T>()
                        .eq(entity.getTableId(), id)
                        .eq(BaseEntity.SYSTEM_VERSION, systemVersion)
                        .eq(BaseEntity.SYSTEM_STATUS, true)
        ) != 0;
        return success;
    }

    public Boolean delete(String id, String systemUpdateUserId, Integer systemVersion) {
        entity.setSystemUpdateUserId(systemUpdateUserId);
        entity.setSystemUpdateTime(new Date());
        entity.setSystemVersion(systemVersion + 1);
        entity.setSystemStatus(false);

        Boolean success = mapper.update(
                entity,
                new EntityWrapper<T>()
                        .eq(entity.getTableId(), id)
                        .eq(BaseEntity.SYSTEM_VERSION, systemVersion)
                        .eq(BaseEntity.SYSTEM_STATUS, true)
        ) != 0;
        return success;
    }

}
