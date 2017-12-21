package com.nowui.cloud.app.app.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.app.app.entity.AppConfig;
import com.nowui.cloud.app.app.mapper.AppConfigMapper;
import com.nowui.cloud.app.app.service.AppConfigService;
import com.nowui.cloud.service.impl.BaseServiceImpl;

/**
 * 
 * @author marcus
 * @since 2017-12-20
 */
@Service
public class AppConfigServiceImpl extends BaseServiceImpl<AppConfigMapper, AppConfig> implements AppConfigService {

    @Override
    public Integer adminCount(String appId, String configCategoryId, String configKey, Boolean configIsDisabled) {
        Integer count = mapper.selectCount(
                new EntityWrapper<AppConfig>()
                        .eq("appId", appId)
                        .eq("configCategoryId", configCategoryId)
                        .like("configKey", configKey)
                        .eq("configIsDisabled", configIsDisabled)
                        .eq("systemStatus", true)
        );
        return count;
    }

    @Override
    public List<AppConfig> adminList(String appId, String configCategoryId, String configKey, Boolean configIsDisabled,
            Integer m, Integer n) {
        List<AppConfig> appConfigList = mapper.selectPage(
                new Page<AppConfig>(m, n),
                new EntityWrapper<AppConfig>()
                        .eq("appId", appId)
                        .eq("configCategoryId", configCategoryId)
                        .like("configKey", configKey)
                        .eq("configIsDisabled", configIsDisabled)
                        .eq("systemStatus", true)
                        .orderDesc(Arrays.asList("systemCreateTime"))
        );
        return appConfigList;
    }

}
