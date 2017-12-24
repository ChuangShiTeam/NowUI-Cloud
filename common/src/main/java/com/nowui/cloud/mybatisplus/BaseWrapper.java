package com.nowui.cloud.mybatisplus;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;

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
}
