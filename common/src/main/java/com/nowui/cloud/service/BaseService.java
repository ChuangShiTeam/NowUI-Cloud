package com.nowui.cloud.service;

import com.nowui.cloud.entity.BaseEntity;

/**
 * @author ZhongYongQiang
 */
public abstract interface BaseService<T extends BaseEntity> {

    /**
     * 商品查询
     *
     * @param id 商品编号
     * @return Product 商品
     */
    T find(String id);

    /**
     * 商品查询
     *
     * @param id 商品编号
     * @param systemStatus 商品编号
     * @return Product 商品
     */
    T find(String id, Boolean systemStatus);

    /**
     * 商品新增
     *
     * @param product 商品
     * @param systemCreateUserId 创建人编号
     * @return Boolean 是否成功
     */
    Boolean save(T product, String systemCreateUserId);

    /**
     * 商品修改
     *
     * @param product 商品
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    Boolean update(T product, String id, String systemUpdateUserId, Integer systemVersion);

    /**
     * 商品删除
     *
     * @param id 商品编号
     * @param systemUpdateUserId 更新人编号
     * @param systemVersion 版本号
     * @return 是否成功
     */
    Boolean delete(String id, String systemUpdateUserId, Integer systemVersion);

}