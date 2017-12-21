package com.nowui.cloud.app.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.nowui.cloud.app.app.entity.AppConfigCategory;
import com.nowui.cloud.mapper.BaseMapper;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Mapper
@Component(value = "appConfigCategoryMapper")
public interface AppConfigCategoryMapper extends BaseMapper<AppConfigCategory> {

}
