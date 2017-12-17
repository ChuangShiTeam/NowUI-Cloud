package com.nowui.cloud.cms.banner.service.impl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.nowui.cloud.cms.banner.entity.Banner;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nowui.cloud.cms.banner.service.BannerService;
import com.nowui.cloud.cms.banner.mapper.BannerMapper;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService{

    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public Integer adminBannerCount(String appId, String bannerTitle) {
        Integer count = bannerMapper.selectCount(
                new EntityWrapper<Banner>()
                        .eq("appId",appId)
                        .like("bannerTitle",bannerTitle)
        );

        return count;
    }

    @Override
    public List<Banner> adminBannerList(String appId, String bannerTitle, Integer pageIndex, Integer pageSize) {
        List<Banner> bannerList = bannerMapper.selectPage(
                new Page<Banner>(pageIndex,pageSize),
                new EntityWrapper<Banner>()
                .eq("appId",appId)
                .like("bannerTitle",bannerTitle)
        );
        return bannerList;
    }

//    @Override
//    public List<Banner> Query(String appId, String title, Integer pageIndex, Integer pageSize) {
//        List<Banner> list = bannerMapper.selectPage(
//                new Page<Banner>(pageIndex, pageSize),
//                new EntityWrapper<Banner>().eq("appId", appId).eq("bannerTitle", title)
//        );
//        return list;
//    }

    // 1、根据AppId 查询Banner图片.
    // 2、参数定义成对象.
    @Override
    public List<Banner> QueryBannerByAppId(String appId){
        // 验证appId 不能为空返回.
        List<Banner> list = bannerMapper.selectList(
                new EntityWrapper<Banner>().eq("appId", appId).eq("isOpen",1)
        );

        return list;
    }

    @Override
    public Banner find(String bannerId){
        Banner banner = bannerMapper.selectById(bannerId);
        return banner;
    }

    @Override
    public Boolean save(Banner banner){
        Boolean success = bannerMapper.insert(banner) != 0;
        return success;
    }

    @Override
    public Boolean update(Banner banner) {
        Boolean success = bannerMapper.updateById(banner) != 0;
        return success;
    }

    @Override
    public Boolean delete(String bannerId) {
        Boolean success = bannerMapper.deleteById(bannerId) != 0;
        return success;
    }
}