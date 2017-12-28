package com.nowui.cloud.mybatisplus;

import java.util.Collection;
import java.util.Map;

import com.baomidou.mybatisplus.entity.Column;
import com.baomidou.mybatisplus.entity.Columns;
import com.baomidou.mybatisplus.enums.SqlLike;
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
            return this.isWhere != null ? (this.isWhere ? sqlWhere : sqlWhere.replaceFirst("WHERE", this.AND_OR))
                    : sqlWhere.replaceFirst("WHERE", this.AND_OR);
        }
    }
    
    public BaseWrapper<T> eqAllowEmpty(String column, Object params) {
        if (!Util.isNullOrEmpty(column)) {
            eq(column, params);
        }
        return this;
    }

    @Override
    public BaseWrapper<T> setSqlSelect(String sqlSelect) {

        super.setSqlSelect(sqlSelect);
        return this;
    }

    @Override
    public BaseWrapper<T> setSqlSelect(String... columns) {

        super.setSqlSelect(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> setSqlSelect(Column... column) {

        super.setSqlSelect(column);
        return this;
    }

    @Override
    public BaseWrapper<T> setSqlSelect(Columns columns) {

        super.setSqlSelect(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> where(boolean condition, String sqlWhere, Object... params) {

        super.where(condition, sqlWhere, params);
        return this;
    }

    @Override
    public BaseWrapper<T> where(String sqlWhere, Object... params) {

        super.where(sqlWhere, params);
        return this;
    }

    @Override
    public BaseWrapper<T> eq(boolean condition, String column, Object params) {

        super.eq(condition, column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> eq(String column, Object params) {

        super.eq(column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> ne(boolean condition, String column, Object params) {

        super.ne(condition, column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> ne(String column, Object params) {

        super.ne(column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> allEq(boolean condition, Map<String, Object> params) {

        super.allEq(condition, params);
        return this;
    }

    @Override
    public BaseWrapper<T> allEq(Map<String, Object> params) {

        super.allEq(params);
        return this;
    }

    @Override
    public BaseWrapper<T> gt(boolean condition, String column, Object params) {

        super.gt(condition, column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> gt(String column, Object params) {

        super.gt(column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> ge(boolean condition, String column, Object params) {

        super.ge(condition, column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> ge(String column, Object params) {

        super.ge(column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> lt(boolean condition, String column, Object params) {

        super.lt(condition, column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> lt(String column, Object params) {

        super.lt(column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> le(boolean condition, String column, Object params) {

        super.le(condition, column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> le(String column, Object params) {

        super.le(column, params);
        return this;
    }

    @Override
    public BaseWrapper<T> and(boolean condition, String sqlAnd, Object... params) {

        super.and(condition, sqlAnd, params);
        return this;
    }

    @Override
    public BaseWrapper<T> and(String sqlAnd, Object... params) {

        super.and(sqlAnd, params);
        return this;
    }

    @Override
    public BaseWrapper<T> andNew(boolean condition, String sqlAnd, Object... params) {

        super.andNew(condition, sqlAnd, params);
        return this;
    }

    @Override
    public BaseWrapper<T> andNew() {

        super.andNew();
        return this;
    }

    @Override
    public BaseWrapper<T> andNew(String sqlAnd, Object... params) {

        super.andNew(sqlAnd, params);
        return this;
    }

    @Override
    public BaseWrapper<T> and() {

        super.and();
        return this;
    }

    @Override
    public BaseWrapper<T> or() {

        super.or();
        return this;
    }

    @Override
    public BaseWrapper<T> or(boolean condition, String sqlOr, Object... params) {

        super.or(condition, sqlOr, params);
        return this;
    }

    @Override
    public BaseWrapper<T> or(String sqlOr, Object... params) {

        super.or(sqlOr, params);
        return this;
    }

    @Override
    public BaseWrapper<T> orNew() {

        super.orNew();
        return this;
    }

    @Override
    public BaseWrapper<T> orNew(boolean condition, String sqlOr, Object... params) {

        super.orNew(condition, sqlOr, params);
        return this;
    }

    @Override
    public BaseWrapper<T> orNew(String sqlOr, Object... params) {

        super.orNew(sqlOr, params);
        return this;
    }

    @Override
    public BaseWrapper<T> groupBy(boolean condition, String columns) {

        super.groupBy(condition, columns);
        return this;
    }

    @Override
    public BaseWrapper<T> groupBy(String columns) {

        super.groupBy(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> having(boolean condition, String sqlHaving, Object... params) {

        super.having(condition, sqlHaving, params);
        return this;
    }

    @Override
    public BaseWrapper<T> having(String sqlHaving, Object... params) {

        super.having(sqlHaving, params);
        return this;
    }

    @Override
    public BaseWrapper<T> orderBy(boolean condition, String columns) {

        super.orderBy(condition, columns);
        return this;
    }

    @Override
    public BaseWrapper<T> orderBy(String columns) {

        super.orderBy(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> orderBy(boolean condition, String columns, boolean isAsc) {

        super.orderBy(condition, columns, isAsc);
        return this;
    }

    @Override
    public BaseWrapper<T> orderBy(boolean condition, Collection<String> columns, boolean isAsc) {

        super.orderBy(condition, columns, isAsc);
        return this;
    }

    @Override
    public BaseWrapper<T> orderBy(String columns, boolean isAsc) {

        super.orderBy(columns, isAsc);
        return this;
    }

    @Override
    public BaseWrapper<T> orderAsc(Collection<String> columns) {

        super.orderAsc(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> orderDesc(Collection<String> columns) {

        super.orderDesc(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> like(boolean condition, String column, String value) {

        super.like(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> like(String column, String value) {

        super.like(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notLike(boolean condition, String column, String value) {

        super.notLike(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notLike(String column, String value) {

        super.notLike(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> like(boolean condition, String column, String value, SqlLike type) {

        super.like(condition, column, value, type);
        return this;
    }

    @Override
    public BaseWrapper<T> like(String column, String value, SqlLike type) {

        super.like(column, value, type);
        return this;
    }

    @Override
    public BaseWrapper<T> notLike(boolean condition, String column, String value, SqlLike type) {

        super.notLike(condition, column, value, type);
        return this;
    }

    @Override
    public BaseWrapper<T> notLike(String column, String value, SqlLike type) {

        super.notLike(column, value, type);
        return this;
    }

    @Override
    public BaseWrapper<T> isNotNull(boolean condition, String columns) {

        super.isNotNull(condition, columns);
        return this;
    }

    @Override
    public BaseWrapper<T> isNotNull(String columns) {

        super.isNotNull(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> isNull(boolean condition, String columns) {

        super.isNull(condition, columns);
        return this;
    }

    @Override
    public BaseWrapper<T> isNull(String columns) {

        super.isNull(columns);
        return this;
    }

    @Override
    public BaseWrapper<T> exists(boolean condition, String value) {

        super.exists(condition, value);
        return this;
    }

    @Override
    public BaseWrapper<T> exists(String value) {

        super.exists(value);
        return this;
    }

    @Override
    public BaseWrapper<T> notExists(boolean condition, String value) {

        super.notExists(condition, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notExists(String value) {

        super.notExists(value);
        return this;
    }

    @Override
    public BaseWrapper<T> in(boolean condition, String column, String value) {

        super.in(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> in(String column, String value) {

        super.in(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notIn(boolean condition, String column, String value) {

        super.notIn(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notIn(String column, String value) {

        super.notIn(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> in(boolean condition, String column, Collection<?> value) {

        super.in(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> in(String column, Collection<?> value) {

        super.in(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notIn(boolean condition, String column, Collection<?> value) {

        super.notIn(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notIn(String column, Collection<?> value) {

        super.notIn(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> in(boolean condition, String column, Object[] value) {

        super.in(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> in(String column, Object[] value) {

        super.in(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notIn(boolean condition, String column, Object... value) {

        super.notIn(condition, column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> notIn(String column, Object... value) {

        super.notIn(column, value);
        return this;
    }

    @Override
    public BaseWrapper<T> between(boolean condition, String column, Object val1, Object val2) {

        super.between(condition, column, val1, val2);
        return this;
    }

    @Override
    public BaseWrapper<T> between(String column, Object val1, Object val2) {

        super.between(column, val1, val2);
        return this;
    }

    @Override
    public BaseWrapper<T> notBetween(boolean condition, String column, Object val1, Object val2) {

        super.notBetween(condition, column, val1, val2);
        return this;
    }

    @Override
    public BaseWrapper<T> notBetween(String column, Object val1, Object val2) {

        super.notBetween(column, val1, val2);
        return this;
    }

    @Override
    public BaseWrapper<T> addFilter(String sqlWhere, Object... params) {

        super.addFilter(sqlWhere, params);
        return this;
    }

    @Override
    public BaseWrapper<T> addFilterIfNeed(boolean need, String sqlWhere, Object... params) {

        super.addFilterIfNeed(need, sqlWhere, params);
        return this;
    }

    @Override
    public BaseWrapper<T> isWhere(Boolean bool) {

        super.isWhere(bool);
        return this;
    }

    @Override
    public BaseWrapper<T> last(String limit) {

        super.last(limit);
        return this;
    }

    @Override
    public BaseWrapper<T> setParamAlias(String paramAlias) {

        super.setParamAlias(paramAlias);
        return this;
    }

}
