package com.nowui.cloud.app.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.app.app.entity.AppConfigCategory;
import com.nowui.cloud.app.app.mapper.AppConfigCategoryMapper;
import com.nowui.cloud.app.app.service.AppConfigCategoryService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigCategoryServiceImpl extends BaseServiceImpl<AppConfigCategoryMapper, AppConfigCategory> implements AppConfigCategoryService {

    @Override
    public Integer adminCount(String appId, String configCategoryName, String configCategoryCode) {
        Integer count = mapper.selectCount(
                new EntityWrapper<AppConfigCategory>()
                        .eq("appId", appId)
                        .like("configCategoryName", configCategoryName)
                        .like("configCategoryCode", configCategoryCode)
                        .eq("systemStatus", true)
        );
        return count;
    }

    @Override
    public List<AppConfigCategory> adminList(String appId, String configCategoryName, String configCategoryCode,
            Integer m, Integer n) {
        List<AppConfigCategory> appCategoryList = mapper.selectPage(
                new Page<AppConfigCategory>(m, n),
                new EntityWrapper<AppConfigCategory>()
                        .eq("appId", appId)
                        .like("configCategoryName", configCategoryName)
                        .like("configCategoryCode", configCategoryCode)
                        .eq("systemStatus", true)
                        .orderDesc(Arrays.asList("systemCreateTime"))
        );
        return appCategoryList;
    }

}
