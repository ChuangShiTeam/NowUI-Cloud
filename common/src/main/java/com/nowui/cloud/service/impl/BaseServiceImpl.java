package com.nowui.cloud.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.redis.core.RedisTemplate;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.mapper.BaseMapper;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.util.Util;

/**
 * @author ZhongYongQiang
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> implements BaseService<T> {

    @Autowired
    protected M mapper;

    @Autowired
    private T entity;

    @Autowired
    protected RedisTemplate<String, Object> redis;

    @Autowired
    protected ElasticsearchTemplate elasticsearch;

    protected String getItemCacheName(String id) {
        return entity.getTableName() + "_item_" + id;
    }
    
    protected String getItemCacheName(String tableName, String id) {
        return tableName + "_item_" + id;
    }
    
    public Integer count(SearchQuery searchQuery) {
        long count = elasticsearch.count(searchQuery);

        return new Long(count).intValue();
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

        GetQuery getQuery = new GetQuery();
        getQuery.setId(id);
        T baseEntity = (T) elasticsearch.queryForObject(getQuery, entity.getClass());

        if (baseEntity == null) {
            baseEntity = (T) redis.opsForValue().get(getItemCacheName(id));

            if (baseEntity == null) {
                baseEntity = mapper.selectById(id);

                if (baseEntity != null) {
                    redis.opsForValue().set(getItemCacheName(id), baseEntity);
                }
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

        baseEntity.put(entity.getTableId(), id);
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
            elasticsearchSaveOrUpdate(baseEntity, id);
        }

        return success;
    }

    private void elasticsearchSaveOrUpdate(T baseEntity, String id) {
        baseEntity.keepTableFieldValue();

        redis.opsForValue().set(getItemCacheName(id), baseEntity);

        IndexQuery indexQuery = new IndexQueryBuilder().withId(id).withObject(baseEntity).build();
        elasticsearch.index(indexQuery);
    }

    @Override
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

        if (success) {
            redis.delete(getItemCacheName(id));

            elasticsearch.delete(entity.getClass(), id);
        }

        return success;
    }

    @Override
    public void replace(String id) {
        redis.delete(getItemCacheName(id));

        elasticsearch.delete(entity.getClass(), id);

        T baseEntity = mapper.selectById(id);

        elasticsearchSaveOrUpdate(baseEntity, id);
    }

}
