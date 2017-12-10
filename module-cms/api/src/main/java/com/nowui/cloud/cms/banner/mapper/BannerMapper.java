package com.nowui.cloud.cms.banner.mapper;
import com.nowui.cloud.mapper.BaseMapper;
import com.nowui.cloud.cms.banner.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BannerMapper extends BaseMapper<Banner> {

}
