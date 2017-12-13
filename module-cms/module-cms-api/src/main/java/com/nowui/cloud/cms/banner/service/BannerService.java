package com.nowui.cloud.cms.banner.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.cms.banner.entity.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author shawn
 */
@Service
public interface BannerService extends BaseService{
    /**
     * Banner列表
     *
     * @param 应用编号
     * @param Banner标题
     * @param 从m条数据开始
     * @param 取n条数据
     * @return Banner列表
     */
    List<Banner> Query(String appId, String title, Integer m, Integer n);

    /**
     * Banner查询
     *
     * @param 标题
     * @return Banner
     */
    Banner find(String bannerId);

    /**
     *  保存Banner
     *
     * @param Banner
     * @return 标题
     */
    Boolean save(Banner banner);

    /**
     *  更新Banner
     *
     * @param  Banner
     * @return 是否成功
     */
    Boolean update(Banner banner);

    /**
     *  删除Banner
     *
     * @param  Banner
     * @return 是否成功
     */
    Boolean delete(String bannerId);
}
