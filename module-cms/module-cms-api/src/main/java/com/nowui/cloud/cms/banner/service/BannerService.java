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
    List<Banner> Query(String appId, String title, Integer m, Integer n);
}
