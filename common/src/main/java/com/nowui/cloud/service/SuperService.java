package com.nowui.cloud.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nowui.cloud.entity.BaseEntity;
import com.nowui.cloud.view.BaseView;

/**
 * SuperService
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public interface SuperService<E extends BaseEntity, V extends BaseView> {

    /**
     * 统计查询
     *
     * @param query
     * @return
     */
    Integer count(Query query);

    /**
     * 统计查询
     *
     * @param var1
     * @return
     */
    Integer count(@Param("ew") Wrapper<E> var1);

    /**
     * 列表查询
     *
     * @param query
     * @return
     */
    List<V> list(Query query);
    
    /**
     * 列表查询
     * 
     * @param query
     * @param sort
     * @return
     */
    List<V> list(Query query, Sort sort);

    /**
     * 分页列表查询
     *
     * @param query
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<V> list(Query query, Sort sort, Integer pageIndex, Integer pageSize);

    /**
     * 分页列表查询
     *
     * @param var1
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<E> list(@Param("ew") Wrapper<E> var1, Integer pageIndex, Integer pageSize);

    /**
     * 列表查询
     *
     * @param var1
     * @return
     */
    List<E> list(@Param("ew") Wrapper<E> var1);

    /**
     * 所有Mysql数据
     *
     * @return
     */
    List<E> listByMysql();

    /**
     * 单个实体类查询
     *
     * @param var1
     * @return
     */
    E find(@Param("ew") Wrapper<E> var1);

    /**
     * 单个实体类查询
     *
     * @param id 编号
     * @return E 单个实体类
     */
    V find(String id);

    /**
     * 单个实体类查询
     *
     * @param id 编号
     * @return E 单个实体类
     */
    E findByMysql(String id);

    /**
     * 单个实体类查询
     *
     * @param id 商品编号
     * @param systemStatus 状态
     * @return E 单个实体类
     */
    E find(String id, Boolean systemStatus);

//    /**
//     * 实体类新增
//     *
//     * @param entity 实体类
//     * @param id 实体类编号
//     * @param systemCreateUserId 创建人编号
//     * @return Boolean 是否成功
//     */
//    Boolean save(E entity, String id, String systemCreateUserId);

    /**
     * 实体类新增
     *
     * @param view 视图类
     * @return 是否成功
     */
    Boolean save(V view);

//    /**
//     * 实体类新增
//     *
//     * @param entity 实体类
//     * @param id 实体类编号
//     * @param systemCreateUserId 创建人编号
//     * @return Boolean 是否成功
//     */
//    Boolean save(E entity, String id, String systemCreateUserId);

    /**
     * 实体类新增
     *
     * @param entity 实体类
     * @param id 实体类编号
     * @param systemCreateUserId 创建人编号
     * @return Boolean 是否成功
     */
    E save(E entity, String id, String systemCreateUserId);

//    /**
//     * 实体类新增
//     *
//     * @param entity 实体类
//     * @param id 实体类编号
//     * @param appId 应用编号
//     * @param routing 路由
//     * @param systemCreateUserId 创建人编号
//     * @return Boolean 是否成功
//     */
//    Boolean save(E entity, String id, String appId, String routing, String systemCreateUserId);

    /**
     * 实体类修改
     *
     * @param view 视图类
     * @return 是否成功
     */
    Boolean update(V view);

    /**
     * 实体类修改
     *
     * @param entity 实体类
     * @param id 实体类编号
     * @param systemUpdateUserId 更新人编号
     * @return 是否成功
     */
    Boolean update(E entity, String id, String systemUpdateUserId);

    /**
     * 实体类修改
     *
     * @param entity 实体类
     * @param id 实体类编号
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    E update(E entity, String id, String systemUpdateUserId, Integer systemVersion);

//    /**
//     * 实体类修改
//     *
//     * @param entity 实体类
//     * @param id 实体类编号
//     * @param appId 应用编号
//     * @param routing 路由
//     * @param systemUpdateUserId 更新人编号
//     * @param systemVersion 版本号
//     * @return 是否成功
//     */
//    Boolean update(E entity, String id, String appId, String routing, String systemUpdateUserId, Integer systemVersion);

//    /**
//     * 实体类删除
//     *
//     * @param id 实体类编号
//     * @param systemUpdateUserId 更新人编号
//     * @param systemVersion 版本号
//     * @return 是否成功
//     */
//    Boolean delete(String id, String systemUpdateUserId, Integer systemVersion);

//    /**
//     * 实体类删除
//     *
//     * @param id 实体类编号
//     * @param appId 应用编号
//     * @param routing 路由
//     * @param systemUpdateUserId 更新人编号
//     * @return 是否成功
//     */
//    Boolean delete(String id, String appId, String routing, String systemUpdateUserId);

    /**
     * 实体类删除
     *
     * @param id 实体类编号
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    E delete(String id, String systemUpdateUserId, Integer systemVersion);


    /**
     * 搜索、缓存更新
     *
     * @param id 实体类编号
     * @return 是否成功
     */
    void replace(String id);

}
