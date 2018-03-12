package com.nowui.cloud.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mongodb.client.result.UpdateResult;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.mapper.BaseMapper;
import com.nowui.cloud.mongodb.BasePageable;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.rabbit.RabbitSender;
import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.BaseView;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * SuperService
 *
 * @author ZhongYongQiang
 * <p>
 * 2018-01-29
 */
public class SuperServiceImpl<M extends BaseMapper<E>, E extends BaseEntity, R extends BaseRepository<V>, V extends BaseView> extends ServiceImpl<M, E> implements SuperService<E, V> {

    @Autowired
    private E entity;

    @Autowired
    private V view;

    @Autowired
    protected M mapper;

    @Autowired
    protected R repository;

    @Autowired
    protected RabbitSender rabbitSender;

//    @Autowired
//    protected RedisTemplate<String, Object> redisTemplate;

    @Autowired
    protected MongoTemplate mongoTemplate;

//    protected String getItemCacheName(String id) {
//        return entity.getTableName() + "_item_" + id;
//    }

//    protected String getItemCacheName(String tableName, String id) {
//        return tableName + "_item_" + id;
//    }

    @Override
    public Integer count(Query query) {
        long result = mongoTemplate.count(query, view.getClass());

        return new Long(result).intValue();
    }

    @Override
    public Integer count(@Param("ew") Wrapper<E> var1) {
        Integer count = mapper.selectCount(var1);
        return count;
    }

    @Override
    public List<V> list(Query query) {
        List<V> resultList = (List<V>) mongoTemplate.find(query, view.getClass());

        return resultList;
    }

    @Override
    public List<V> list(Query query, Sort sort) {
        query.with(sort);

        List<V> resultList = (List<V>) mongoTemplate.find(query, view.getClass());

        return resultList;
    }

    @Override
    public List<V> list(Query query, Sort sort, Integer pageIndex, Integer pageSize) {
        BasePageable basePageable = new BasePageable();
        basePageable.setSort(sort);
        basePageable.setIndex(pageIndex);
        basePageable.setSize(pageSize);

        query.with(basePageable);

        List<V> resultList = (List<V>) mongoTemplate.find(query, view.getClass());

        return resultList;
    }

    @Override
    public List<E> list(@Param("ew") Wrapper<E> var1, Integer m, Integer n) {
        List<E> list = mapper.selectPage(new Page<E>(m, n), var1);

        return list;
    }

    @Override
    public List<E> list(@Param("ew") Wrapper<E> var1) {
        List<E> list = mapper.selectList(var1);

        return list;
    }

    @Override
    public List<E> listByMysql() {
        List<E> list = mapper.selectList(new BaseWrapper<E>());

        return list;
    }

    @Override
    public E find(@Param("ew") Wrapper<E> var1) {
        List<E> list = mapper.selectList(var1.setSqlSelect(entity.tableId()));

        if (list == null || list.size() == 0) {
            return null;
        }

        return list.get(0);
    }

    @Override
    public V find(Query query) {
        List<V> resultList = (List<V>) mongoTemplate.find(query, view.getClass());

        if (Util.isNullOrEmpty(resultList)) {
            return null;
        }
        return resultList.get(0);
    }

    @Override
    public V find(String id) {
        if (Util.isNullOrEmpty(id)) {
            return null;
        }

        Criteria criteria = Criteria.where(view.tableId()).is(id);
        Query query = new Query();
        query.addCriteria(criteria);

        V result = (V) mongoTemplate.findOne(query, view.getClass());

        return result;
    }

    @Override
    public V find(String id, String appId) {
        if (Util.isNullOrEmpty(id)) {
            return null;
        }

        Criteria criteria = Criteria.where(view.tableId()).is(id)
                .and(Constant.APP_ID).is(appId);
        Query query = new Query();
        query.addCriteria(criteria);

        V result = (V) mongoTemplate.findOne(query, view.getClass());

        return result;
    }

    @Override
    public E findByMysql(String id) {
        if (Util.isNullOrEmpty(id)) {
            return null;
        }

        List<E> list = mapper.selectList(
                new EntityWrapper<E>()
                        .eq(entity.tableId(), id)
        );

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public E find(String id, Boolean systemStatus) {
        if (Util.isNullOrEmpty(id)) {
            return null;
        }

        List<E> list = mapper.selectList(
                new EntityWrapper<E>()
                        .eq(entity.tableId(), id)
                        .eq(Constant.SYSTEM_STATUS, systemStatus)
        );

        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public Boolean save(V view) {
        mongoTemplate.save(view);

        return true;
    }

    @Override
    public Boolean save(List<V> viewList) {
        mongoTemplate.insertAll(viewList);

        return true;
    }

//    @Override
//    public Boolean save(E baseEntity, String id, String systemCreateUserId) {
//        baseEntity.put(entity.tableId(), id);
//        baseEntity.setSystemCreateUserId(systemCreateUserId);
//        baseEntity.setSystemCreateTime(new Date());
//        baseEntity.setSystemUpdateUserId(systemCreateUserId);
//        baseEntity.setSystemUpdateTime(new Date());
//        baseEntity.setSystemVersion(0);
//        baseEntity.setSystemStatus(true);
//
//        Boolean success = mapper.insert(baseEntity) != 0;
//
//        if (success) {
//
//        }
//
//        return success;
//    }

    @Override
    public E save(E baseEntity, String id, String systemCreateUserId) {
        try {
            String method = "set" + baseEntity.tableId().substring(0, 1).toUpperCase() + baseEntity.tableId().substring(1);
            Method setId = baseEntity.getClass().getMethod(method, String.class);
            setId.invoke(baseEntity, id);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        baseEntity.put(baseEntity.tableId(), id);
        baseEntity.setSystemCreateUserId(systemCreateUserId);
        baseEntity.setSystemCreateTime(new Date());
        baseEntity.setSystemUpdateUserId(systemCreateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(0);
        baseEntity.setSystemStatus(true);

        Boolean success = mapper.insert(baseEntity) != 0;

        if (success) {
            return baseEntity;
        } else {
            return null;
        }
    }

    @Override
    public List<E> save(List<E> list, String systemCreateUserId) {
        if (list.size() == 0) {
            return null;
        }

        for (E baseEntity : list) {
            baseEntity.setSystemCreateUserId(systemCreateUserId);
            baseEntity.setSystemCreateTime(new Date());
            baseEntity.setSystemUpdateUserId(systemCreateUserId);
            baseEntity.setSystemUpdateTime(new Date());
            baseEntity.setSystemVersion(0);
            baseEntity.setSystemStatus(true);
        }

        Boolean success = insertBatch(list);

        if (success) {
            return list;
        } else {
            return null;
        }
    }

//    @Override
//    public Boolean save(E baseEntity, String id, String appId, String routing, String systemCreateUserId) {
//        baseEntity.put(entity.tableId(), id);
//        baseEntity.setSystemCreateUserId(systemCreateUserId);
//        baseEntity.setSystemCreateTime(new Date());
//        baseEntity.setSystemUpdateUserId(systemCreateUserId);
//        baseEntity.setSystemUpdateTime(new Date());
//        baseEntity.setSystemVersion(0);
//        baseEntity.setSystemStatus(true);
//
//        Boolean success = mapper.insert(baseEntity) != 0;
//
//        if (success) {
//            rabbitSender.send(appId, routing, baseEntity, systemCreateUserId);
//        }
//
//        return success;
//    }

    @Override
    public Boolean saveOrUpdate(V view, String id) {
        Criteria criteria = Criteria.where(view.tableId()).is(id);
        Query query = new Query(criteria);
        Update update = new Update();

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(view);

        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();

            if (!Util.isNullOrEmpty(entry.getValue())) {
                update.set(entry.getKey(), entry.getValue());
            }
        }

        UpdateResult updateResult = mongoTemplate.upsert(query, update, view.getClass());

        return updateResult.isModifiedCountAvailable();
    }

    @Override
    public Boolean update(V view, String id) {
        Criteria criteria = Criteria.where(view.tableId()).is(id);
        Query query = new Query(criteria);
        Update update = new Update();

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(view);

        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();

            if (!Util.isNullOrEmpty(entry.getValue())) {
                update.set(entry.getKey(), entry.getValue());
            }
        }

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, view.getClass());

        return updateResult.isModifiedCountAvailable();
    }

    @Override
    public Boolean update(E baseEntity, String id, String systemUpdateUserId) {
        try {
            String method = "set" + baseEntity.tableId().substring(0, 1).toUpperCase() + baseEntity.tableId().substring(1);
            Method setId = baseEntity.getClass().getMethod(method, String.class);
            setId.invoke(baseEntity, id);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


//        baseEntity.put(baseEntity.tableId(), id);
        baseEntity.setSystemUpdateUserId(systemUpdateUserId);
        baseEntity.setSystemUpdateTime(new Date());

        Boolean success = mapper.update(
                baseEntity,
                new EntityWrapper<E>()
                        .eq(entity.tableId(), id)
                        .eq(Constant.SYSTEM_STATUS, true)
        ) != 0;

        if (success) {

        }

        return success;
    }

    @Override
    public E update(E baseEntity, String id, String systemUpdateUserId, Integer systemVersion) {
        try {
            String method = "set" + baseEntity.tableId().substring(0, 1).toUpperCase() + baseEntity.tableId().substring(1);
            Method setId = baseEntity.getClass().getMethod(method, String.class);
            setId.invoke(baseEntity, id);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        baseEntity.put(baseEntity.tableId(), id);
        baseEntity.setSystemUpdateUserId(systemUpdateUserId);
        baseEntity.setSystemUpdateTime(new Date());
        baseEntity.setSystemVersion(systemVersion + 1);

        Boolean success = mapper.update(
                baseEntity,
                new EntityWrapper<E>()
                        .eq(entity.tableId(), id)
                        .eq(Constant.SYSTEM_VERSION, systemVersion)
                        .eq(Constant.SYSTEM_STATUS, true)
        ) != 0;

        if (success) {
            return baseEntity;
        } else {
            return null;
        }
    }

    @Override
    public E update(E entity, Wrapper<E> var1, String systemUpdateUserId) {
        entity.setSystemUpdateUserId(systemUpdateUserId);
        entity.setSystemUpdateTime(new Date());

        Boolean success = mapper.update(
                entity,
                var1
        ) != 0;

        if (success) {
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public Boolean delete(V view, String id) {
        Criteria criteria = Criteria.where(view.tableId()).is(id);
        Query query = new Query(criteria);
        Update update = new Update();

        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(view);

        Iterator<Map.Entry<String, Object>> iterator = jsonObject.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();

            update.set(entry.getKey(), entry.getValue());
        }
        update.set(BaseView.SYSTEM_STATUS, false);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, view.getClass());

        return updateResult.isModifiedCountAvailable();
    }

//    @Override
//    public Boolean update(E baseEntity, String id, String appId, String routing, String systemUpdateUserId, Integer systemVersion) {
//        baseEntity.setSystemUpdateUserId(systemUpdateUserId);
//        baseEntity.setSystemUpdateTime(new Date());
//        baseEntity.setSystemVersion(systemVersion + 1);
//
//        Boolean success = mapper.update(
//                baseEntity,
//                new EntityWrapper<E>()
//                        .eq(entity.tableId(), id)
//                        .eq(BaseEntity.SYSTEM_VERSION, systemVersion)
//                        .eq(BaseEntity.SYSTEM_STATUS, true)
//        ) != 0;
//
//        if (success) {
//            rabbitSender.send(appId, routing, baseEntity, systemUpdateUserId);
//        }
//
//        return success;
//    }

    private void redisSaveOrUpdate(E baseEntity, String id) {
//        baseEntity.keepTableFieldValue();

//        redisTemplate.opsForValue().set(getItemCacheName(id), baseEntity);
    }

//    @Override
//    public Boolean delete(String id, String systemUpdateUserId, Integer systemVersion) {
//        entity.setSystemUpdateUserId(systemUpdateUserId);
//        entity.setSystemUpdateTime(new Date());
//        entity.setSystemVersion(systemVersion + 1);
//        entity.setSystemStatus(false);
//
//        Boolean success = mapper.update(
//                entity,
//                new EntityWrapper<E>()
//                        .eq(entity.tableId(), id)
//                        .eq(BaseEntity.SYSTEM_VERSION, systemVersion)
//                        .eq(BaseEntity.SYSTEM_STATUS, true)
//        ) != 0;
//
//        if (success) {
//            redis.delete(getItemCacheName(id));
//        }
//
//        return success;
//    }

//    @Override
//    public Boolean delete(String id, String appId, String routing, String systemUpdateUserId) {
//        entity.setSystemUpdateUserId(systemUpdateUserId);
//        entity.setSystemUpdateTime(new Date());
//        entity.setSystemStatus(false);
//
//        Boolean success = mapper.update(
//                entity,
//                new EntityWrapper<E>()
//                        .eq(entity.tableId(), id)
//                        .eq(BaseEntity.SYSTEM_STATUS, true)
//        ) != 0;
//
//        if (success) {
//            rabbitSender.send(appId, routing, entity, systemUpdateUserId);
//        }
//
//        return success;
//    }

    @Override
    public E delete(String id, String systemUpdateUserId, Integer systemVersion) {
        try {
            String name = "set" + entity.tableId().substring(0, 1).toUpperCase() + entity.tableId().substring(1);
            Method method = entity.getClass().getMethod(name, String.class);
            method.invoke(entity, id);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        entity.put(entity.tableId(), id);
        entity.setSystemUpdateUserId(systemUpdateUserId);
        entity.setSystemUpdateTime(new Date());
        entity.setSystemVersion(systemVersion + 1);
        entity.setSystemStatus(false);

        Boolean success = mapper.update(
                entity,
                new EntityWrapper<E>()
                        .eq(entity.tableId(), id)
                        .eq(Constant.SYSTEM_VERSION, systemVersion)
                        .eq(Constant.SYSTEM_STATUS, true)
        ) != 0;

        if (success) {
            return entity;
        } else {
            return null;
        }
    }

    @Override
    public E delete(Wrapper<E> var1, String systemUpdateUserId) {
        entity.setSystemUpdateUserId(systemUpdateUserId);
        entity.setSystemUpdateTime(new Date());
        entity.setSystemStatus(false);

        Boolean success = mapper.update(
                entity,
                var1
        ) != 0;

        if (success) {
            return entity;
        } else {
            return null;
        }
    }

}