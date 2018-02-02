package com.nowui.cloud.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.view.BaseView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 数据访问组件接口实现
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public class BaseRepositoryImpl<T extends BaseView> implements BaseRepository<T> {

    @Autowired
    private T view;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Integer count(Criteria criteria) {
        Query query = new Query(criteria);

        long result = mongoTemplate.count(query, view.getClass());

        return new Long(result).intValue();
    }

    @Override
    public List<T> list(Criteria criteria) {
        Query query = new Query(criteria);
        List<T> resultList = (List<T>) mongoTemplate.find(query, view.getClass());

        return resultList;
    }

    @Override
    public List<T> list(Criteria criteria, Integer m, Integer n) {
        Query query = new Query(criteria);
        List<T> resultList = (List<T>) mongoTemplate.find(query, view.getClass());

        return resultList;
    }

    @Override
    public T find(String id) {
        Criteria criteria = Criteria.where(view.getTableId()).is(view.get(view.getTableId()));
        Query query = new Query(criteria);

        T result = (T) mongoTemplate.findOne(query, view.getClass());

        return result;
    }

    @Override
    public Boolean save(T view) {
        mongoTemplate.save(view);

        return true;
    }

    @Override
    public Boolean update(T view) {
        Criteria criteria = Criteria.where(view.getTableId()).is(view.get(view.getTableId()));
        Query query = new Query(criteria);
        Update update = new Update();

        Iterator<Map.Entry<String, Object>> iterator = view.getInnerMap().entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();

            update.set(entry.getKey(), entry.getValue());
        }

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, view.getClass());

        return updateResult.isModifiedCountAvailable();
    }
}
