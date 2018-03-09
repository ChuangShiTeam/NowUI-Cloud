package com.nowui.cloud.sns.forum.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.sns.forum.entity.ForumBackgroundMedia;
import com.nowui.cloud.sns.forum.mapper.ForumBackgroundMediaMapper;
import com.nowui.cloud.sns.forum.repository.ForumBackgroundMediaRepository;
import com.nowui.cloud.sns.forum.service.ForumBackgroundMediaService;
import com.nowui.cloud.sns.forum.view.ForumBackgroundMediaView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 论坛背景业务实现
 *
 * @author xupengfei
 *
 * 2018-03-09
 */
@Service
public class ForumBackgroundMediaServiceImpl extends SuperServiceImpl<ForumBackgroundMediaMapper, ForumBackgroundMedia, ForumBackgroundMediaRepository, ForumBackgroundMediaView> implements ForumBackgroundMediaService {

    @Override
        public Integer countForAdmin(String appId, String forumId) {
            Criteria criteria = Criteria.where(ForumBackgroundMediaView.APP_ID).is(appId)
                    .and(ForumBackgroundMediaView.FORUM_ID).regex(".*?" + forumId + ".*")
                    .and(ForumBackgroundMediaView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<ForumBackgroundMediaView> listForAdmin(String appId, String forumId, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(ForumBackgroundMedia.APP_ID).is(appId)
                    .and(ForumBackgroundMediaView.FORUM_ID).regex(".*?" + forumId + ".*")
                    .and(ForumBackgroundMediaView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, ForumBackgroundMediaView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<ForumBackgroundMediaView> forumBackgroundMediaViewList = list(query, sort, pageIndex, pageSize);

            return forumBackgroundMediaViewList;
        }

}