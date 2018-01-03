package com.nowui.cloud.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nowui.cloud.entity.BaseEntity;

/**
 * @author ZhongYongQiang
 */
public interface BaseService<T extends BaseEntity> {
    
    /**
     * 统计查询
     * 
     * @param var1
     * @return
     */
    public Integer count(@Param("ew") Wrapper<T> var1);
    
    /**
     * 分页列表查询
     * 
     * @param var1
     * @param m
     * @param n
     * @return
     */
    public List<T> list(@Param("ew") Wrapper<T> var1, Integer m, Integer n);
    
    /**
     * 列表查询
     * 
     * @param var1
     * @return
     */
    public List<T> list(@Param("ew") Wrapper<T> var1);

    /**
     * 单个实体类查询
     *
     * @param id 编号
     * @return T 单个实体类
     */
    T find(String id);

    /**
     * 单个实体类查询
     *
     * @param id 商品编号
     * @param systemStatus 状态
     * @return T 单个实体类
     */
    T find(String id, Boolean systemStatus);

    /**
     * 实体类新增
     *
     * @param entity 实体类
     * @param systemCreateUserId 创建人编号
     * @return Boolean 是否成功
     */
    Boolean save(T entity, String id, String systemCreateUserId);

    /**
     * 实体类修改
     *
     * @param entity 实体类
     * @param id 实体类编号
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    Boolean update(T entity, String id, String systemUpdateUserId, Integer systemVersion);

    /**
     * 实体类删除
     *
     * @param id 实体类编号
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    Boolean delete(String id, String systemUpdateUserId, Integer systemVersion);

}