package com.nowui.cloud.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nowui.cloud.rabbit.RabbitSender;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.mapper.BaseMapper;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.util.Util;

/**
 * BaseService
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    private T entity;

    @Autowired
    protected M mapper;

    @Autowired
    protected RabbitSender rabbitSender;

    @Autowired
    protected RedisTemplate<String, Object> redis;

    protected String getItemCacheName(String id) {
        return entity.getTableName() + "_item_" + id;
    }

    protected String getItemCacheName(String tableName, String id) {
        return tableName + "_item_" + id;
    }

    @Override
    public Integer count(@Param("ew") Wrapper<T> var1) {
        Integer count = mapper.selectCount(var1);
        return count;
    }

    @Override
    public List<T> list(@Param("ew") Wrapper<T> var1, Integer m, Integer n) {
        List<T> list = mapper.selectPage(new Page<T>(m, n), var1.setSqlSelect(entity.getTableId()));

        for (T baseEntity : list) {
            baseEntity.putAll(find(baseEntity.getString(entity.getTableId())));
        }

        return list;
    }

    @Override
    public List<T> list(@Param("ew") Wrapper<T> var1) {
        List<T> list = mapper.selectList(var1.setSqlSelect(entity.getTableId()));

        for (T baseEntity : list) {
            baseEntity.putAll(find(baseEntity.getString(entity.getTableId())));
        }

        return list;
    }

    @Override
    public T find(@Param("ew") Wrapper<T> var1) {
        List<T> list = mapper.selectList(var1.setSqlSelect(entity.getTableId()));

        for (T baseEntity : list) {
            baseEntity.putAll(find(baseEntity.getString(entity.getTableId())));
        }

        if (list == null || list.size() == 0) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public T find(String id) {
        if (Util.isNullOrEmpty(id)) {
            return null;
        }
        
        T baseEntity = (T) redis.opsForValue().get(getItemCacheName(id));

        if (baseEntity == null) {
            baseEntity = mapper.selectById(id);

            if (baseEntity != null) {
                redis.opsForValue().set(getItemCacheName(id), baseEntity);
            }
        }

        return baseEntity;
    }

    @Override
    public T find(String id, Boolean systemStatus) {
        if (Util.isNullOrEmpty(id)) {
            return null;
        }

        T baseEntity = (T) redis.opsForValue().get(getItemCacheName(id));

        if (baseEntity == null) {
            List<T> list = mapper.selectList(
                    new EntityWrapper<T>()
                            .eq(entity.getTableId(), id)
                            .eq(BaseEntity.SYSTEM_STATUS, systemStatus)
            );
            if (list.size() == 0) {
                return null;
            } else {
                baseEntity = list.get(0);

                redis.opsForValue().set(getItemCacheName(id), baseEntity);

                return baseEntity;
            }
        } else {
            if (baseEntity.getSystemStatus().equals(systemStatus)) {
                return baseEntity;
            } else {
                return null;
            }
        }
    }

    @Override
    public Boolean save(T baseEntity, String id, String systemCreateUserId) {
        baseEntity.put(baseEntity.getTableId(), id);
        baseEntity.setSystemCreateUserId(systemCreateUserId);
        baseEntity.setSystemCreateTime(new Date());
        baseEntity.setSystemUpdateUserId(systemCreateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(0);
        baseEntity.setSystemStatus(true);

        Boolean success = mapper.insert(baseEntity) != 0;

        if (success) {
            elasticsearchSaveOrUpdate(baseEntity, id);
        }

        return success;
    }

    @Override
    public Boolean update(T baseEntity, String id, String systemUpdateUserId, Integer systemVersion) {
        baseEntity.put(baseEntity.getTableId(), id);
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

        if (success) {
            replace(id);
        }

        return success;
    }

    private void elasticsearchSaveOrUpdate(T baseEntity, String id) {
        baseEntity.keepTableFieldValue();

        redis.opsForValue().set(getItemCacheName(id), baseEntity);
    }

    @Override
    public Boolean delete(String id, String systemUpdateUserId, Integer systemVersion) {
        entity.put(entity.getTableId(), id);
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

        if (success) {
            redis.delete(getItemCacheName(id));
        }

        return success;
    }

    @Override
    public void replace(String id) {
        redis.delete(getItemCacheName(id));

        T baseEntity = mapper.selectById(id);

        elasticsearchSaveOrUpdate(baseEntity, id);
    }

    /**
     * redis获取key下的所有列表
     *
     * @param key 键值
     * @return List<T> 列表数据
     */
    public <T> List<T> lrange(String key) {
        return (List<T>) redis.opsForList().range(key, 0, -1);
    }

    /**
     * redis获取key下的所有列表
     *
     * @param key
     * @return List<Map> 列表数据
     */
    public <Map> List<Map> lrangeToMap(String key) {
        return (List<Map>) redis.opsForList().range(key, 0, -1);
    }

    /**
     * redis获取key下的分页列表
     *
     * @param key 键值
     * @return List<T> 列表数据
     */
    public <T> List<T> lrange(String key, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex <= 0 || pageSize == null || pageSize <= 0) {
            return new ArrayList<T>();
        }

        int start = pageIndex - 1;
        int end = (pageIndex * pageSize) - 1;

        return (List<T>) redis.opsForList().range(key, start, end);
    }

    /**
     * redis获取key下的分页列表
     *
     * @param key
     * @return List<Map> 列表数据
     */
    public <Map> List<Map> lrangeToMap(String key, Integer pageIndex, Integer pageSize) {
        if (pageIndex == null || pageIndex <= 0 || pageSize == null || pageSize <= 0) {
            return new ArrayList<Map>();
        }

        int start = pageIndex - 1;
        int end = (pageIndex * pageSize) - 1;

        return (List<Map>) redis.opsForList().range(key, start, end);
    }

    /**
     * redis获取key下列表的大小
     *
     * @param key 键值
     * @return int 列表大小
     */
    public int lsize(final String key) {
        long size = redis.opsForList().size(key);
        return (int) size;
    }

}
