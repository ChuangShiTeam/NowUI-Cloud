package com.nowui.cloud.app.app.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.nowui.cloud.app.app.entity.App;
import com.nowui.cloud.mapper.BaseMapper;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Mapper
@Component(value = "appMapper")
public interface AppMapper extends BaseMapper<App> {

}
