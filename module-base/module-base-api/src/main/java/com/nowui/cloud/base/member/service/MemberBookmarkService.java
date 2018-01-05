package com.nowui.cloud.base.member.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.member.entity.MemberBookmark;

import java.util.List;

/**
 * 会员收藏业务接口
 *
 * @author marcus
 *
 * 2018-01-02
 */
public interface MemberBookmarkService extends BaseService<MemberBookmark> {

    /**
     * 会员收藏统计
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberBookmarkTitle 会员收藏标题
     * @return Integer 会员收藏统计
     */
    Integer adminCount(String appId, String memberId, String memberBookmarkTitle);

    /**
     * 会员收藏列表
     *
     * @param appId 应用编号
     * @param memberId 会员编号
     * @param memberBookmarkTitle 会员收藏标题
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<MemberBookmark> 会员收藏列表
     */
    List<MemberBookmark> adminList(String appId, String memberId, String memberBookmarkTitle, Integer pageIndex, Integer pageSize);
}
