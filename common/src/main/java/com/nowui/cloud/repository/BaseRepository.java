package com.nowui.cloud.repository;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.nowui.cloud.view.BaseView;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

/**
 * 数据访问组件接口
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public interface BaseRepository<T extends BaseView> {

    /**
     * 统计查询
     *
     * @param criteria
     * @return
     */
    Integer count(Criteria criteria);

    /**
     * 列表查询
     *
     * @param criteria
     * @return
     */
    List<T> list(Criteria criteria);

    /**
     * 分页列表查询
     *
     * @param criteria
     * @param m
     * @param n
     * @return
     */
    List<T> list(Criteria criteria, Integer m, Integer n);

    /**
     * 单个实体类查询
     *
     * @param id 视图类编号
     * @return T 单个实体类
     */
    T find(String id);

    /**
     * 实体类新增
     *
     * @param view 视图类
     * @return Boolean 是否成功
     */
    Boolean save(T view);

    /**
     * 实体类修改
     *
     * @param view 视图类
     * @return 是否成功
     */
    Boolean update(T view);

    /**
     * 实体类删除
     *
     * @param id 视图类编号
     * @return 是否成功
     */
    Boolean delete(String id);

}
