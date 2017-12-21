package com.nowui.cloud.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.mapper.BaseMapper;

/**
 * @author ZhongYongQiang
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> {

    @Autowired
    protected M mapper;

    @Autowired
    private T entity;

    @Cacheable(key = "id")
    public T find(String id) {
        T baseEntity = mapper.selectById(id);
        return baseEntity;
    }

    @Cacheable(key = "id")
    public T find(String id, Boolean systemStatus) {
        T baseEntity = mapper.selectOne(entity);
        return baseEntity;
    }

    public Boolean save(T baseEntity, String systemCreateUserId) {
        baseEntity.setSystemCreateUserId(systemCreateUserId);
        baseEntity.setSystemCreateTime(new Date());
        baseEntity.setSystemUpdateUserId(systemCreateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(0);
        baseEntity.setSystemStatus(true);

        Boolean success = mapper.insert(baseEntity) != 0;
        return success;
    }

    @CacheEvict(key = "id")
    public Boolean update(T baseEntity, String id, String systemUpdateUserId, Integer systemVersion) {
        baseEntity.setSystemUpdateUserId(systemUpdateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(systemVersion + 1);

        Boolean success = mapper.update(
                baseEntity,
                new EntityWrapper<T>()
                        .eq(entity.getPrimary(), id)
                        .eq("systemVersion", systemVersion)
                        .eq("systemStatus", true)
        ) != 0;
        return success;
    }

    @CacheEvict(key = "id")
    public Boolean delete(String id, String systemUpdateUserId, Integer systemVersion) {
        entity.setSystemUpdateUserId(systemUpdateUserId);
        entity.setSystemUpdateTime(new Date());
        entity.setSystemVersion(systemVersion + 1);
        entity.setSystemStatus(false);

        Boolean success = mapper.update(
                entity,
                new EntityWrapper<T>()
                        .eq(entity.getPrimary(), id)
                        .eq("systemVersion", systemVersion)
                        .eq("systemStatus", true)
        ) != 0;
        return success;
    }

}
