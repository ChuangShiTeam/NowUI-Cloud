package com.nowui.cloud.cms.banner.service.impl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.cms.banner.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nowui.cloud.cms.banner.service.BannerService;
import com.nowui.cloud.cms.banner.mapper.BannerMapper;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BannerMapper BannerMapper;

    @Override
    public List<Banner> Query(String appId, String title, Integer pageIndex, Integer pageSize) {
        List<Banner> list = BannerMapper.selectPage(
                new Page<Banner>(pageIndex, pageSize),
                new EntityWrapper<Banner>().eq("appId", appId).eq("bannerTitle", title)
        );
        return list;
    }
}
