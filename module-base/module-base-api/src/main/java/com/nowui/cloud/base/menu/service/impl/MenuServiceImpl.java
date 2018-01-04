package com.nowui.cloud.base.menu.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.menu.entity.Menu;
import com.nowui.cloud.base.menu.mapper.MenuMapper;
import com.nowui.cloud.base.menu.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 菜单业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public Integer adminCount(String appId, String menuName) {
        Integer count = count(
                new BaseWrapper<Menu>()
                        .eq(Menu.APP_ID, appId)
                        .likeAllowEmpty(Menu.MENU_NAME, menuName)
                        .eq(Menu.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Menu> adminList(String appId, String menuName, Integer m, Integer n) {
        List<Menu> menuList = list(
                new BaseWrapper<Menu>()
                        .eq(Menu.APP_ID, appId)
                        .likeAllowEmpty(Menu.MENU_NAME, menuName)
                        .eq(Menu.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Menu.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return menuList;
    }

}