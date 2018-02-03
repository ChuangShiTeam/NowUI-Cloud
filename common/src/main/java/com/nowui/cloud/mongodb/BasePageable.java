package com.nowui.cloud.mongodb;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * mongodb查询
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public class BasePageable implements Serializable, Pageable {

    // 当前页
    private Integer index = 1;

    // 当前页面条数
    private Integer size = 0;

    //排序条件
    private Sort sort;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public int getPageNumber() {
        return getIndex();
    }

    @Override
    public int getPageSize() {
        return getSize();
    }

    @Override
    public long getOffset() {
        return (getIndex() - 1) * getSize();
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }

}
