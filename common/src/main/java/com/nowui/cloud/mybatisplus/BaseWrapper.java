package com.nowui.cloud.mybatisplus;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.nowui.cloud.util.Util;

public class BaseWrapper<T> extends Wrapper<T> {

    @Override
    public String getSqlSegment() {
        String sqlWhere = this.sql.toString();
        if (StringUtils.isEmpty(sqlWhere)) {
            return null;
        } else {
            return this.isWhere != null ? (this.isWhere ? sqlWhere : sqlWhere.replaceFirst("WHERE", this.AND_OR)) : sqlWhere.replaceFirst("WHERE", this.AND_OR);
        }
    }
    
    @Override
    public Wrapper<T> eq(String column, Object params) {
        return eq(!Util.isNullOrEmpty(params), column, params);
    }
}
