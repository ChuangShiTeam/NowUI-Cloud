package com.nowui.cloud.member.member.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.member.member.entity.MemberDefaultAvatar;
import com.nowui.cloud.member.member.mapper.MemberDefaultAvatarMapper;
import com.nowui.cloud.member.member.repository.MemberDefaultAvatarRepository;
import com.nowui.cloud.member.member.service.MemberDefaultAvatarService;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户默认头像业务实现
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
@Service
public class MemberDefaultAvatarServiceImpl extends SuperServiceImpl<MemberDefaultAvatarMapper, MemberDefaultAvatar, MemberDefaultAvatarRepository, MemberDefaultAvatarView> implements MemberDefaultAvatarService {

    @Override
        public Integer countForAdmin(String appId, String userAvatarFileId) {
            Criteria criteria = Criteria.where(MemberDefaultAvatarView.APP_ID).is(appId)
                    .and(MemberDefaultAvatarView.USER_AVATAR_FILE_ID).regex(".*?" + userAvatarFileId + ".*")
                    .and(MemberDefaultAvatarView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<MemberDefaultAvatarView> listForAdmin(String appId, String userAvatarFileId, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(MemberDefaultAvatar.APP_ID).is(appId)
                    .and(MemberDefaultAvatarView.USER_AVATAR_FILE_ID).regex(".*?" + userAvatarFileId + ".*")
                    .and(MemberDefaultAvatarView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, MemberDefaultAvatarView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<MemberDefaultAvatarView> memberDefaultAvatarViewList = list(query, sort, pageIndex, pageSize);

            return memberDefaultAvatarViewList;
        }

}