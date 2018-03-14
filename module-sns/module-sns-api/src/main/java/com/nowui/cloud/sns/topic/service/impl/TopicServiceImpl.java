package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.mapper.TopicMapper;
import com.nowui.cloud.sns.topic.repository.TopicRepository;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.sns.topic.view.TopicForumView;
import com.nowui.cloud.sns.topic.view.TopicMediaView;
import com.nowui.cloud.sns.topic.view.TopicUserBookmarkView;
import com.nowui.cloud.sns.topic.view.TopicUserLikeView;
import com.nowui.cloud.sns.topic.view.TopicView;
import com.nowui.cloud.util.DateUtil;
import com.nowui.cloud.util.Util;

/**
 * 话题信息业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicServiceImpl extends BaseServiceImpl<TopicMapper, Topic, TopicRepository, TopicView> implements TopicService {
    
	@Autowired
	private TopicForumService topicForumService;
	
	@Autowired
	private TopicMediaService topicMediaService;

	@Autowired
	private ForumService forumService;
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private TopicUserBookmarkService topicUserBookmarkService;
	
	@Autowired
	private TopicUserLikeService topicUserLikeService;
	
	@Autowired
	private TopicCommentService topicCommentService;
	
	@Autowired
	private TopicUserUnbookmarkService topicUserUnbookmarkService;
	
	@Autowired
	private TopicUserUnlikeService topicUserUnlikeService;
	
	public static final String TOPIC_COUTN_THE_USER_SEND = "topic_count_the_user_send_";

	
    @Override
    public Integer countForAdmin(String appId, String topicSummary, String memberId,  String topicLocation, Boolean topicIsLocation) {
        Integer count = count(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.TOPIC_SUMMARY, topicSummary)
                        .eq(Topic.MEMBER_ID, memberId)
                        .likeAllowEmpty(Topic.TOPIC_LOCATION, topicLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_LOCATION, topicIsLocation)
                        .eq(Topic.SYSTEM_STATUS, true)
        );

        return count;
    }

    @Override
    public List<Topic> listForAdmin(String appId, String topicSummary, String memberId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecommend, Integer topicTopLevel, Integer pageIndex, Integer pageSize) {
        List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.TOPIC_SUMMARY, topicSummary)
                        .eq(Topic.MEMBER_ID, memberId)
                        .likeAllowEmpty(Topic.LATITUDE, latitude)
                        .likeAllowEmpty(Topic.LONGTITUDE, longtitude)
                        .likeAllowEmpty(Topic.TOPIC_LOCATION, topicLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_LOCATION, topicIsLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_TOP, topicIsTop)
                        .eqAllowEmpty(Topic.TOPIC_IS_RECOMAND, topicIsRecommend)
                        .eqAllowEmpty(Topic.TOPIC_TOP_LEVEL, topicTopLevel)
                        .eq(Topic.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicList;
    }
    
    @Override
	public List<Topic> listByMemberId(String appId, String memberId, Integer pageIndex, Integer pageSize) {
    	List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.MEMBER_ID, memberId)
                        .eq(Topic.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicList;
	}

	@Override
	public List<TopicView> listByForumId(String appId, String forumId, String memberId, Integer pageIndex, Integer pageSize) {
	    
		List<TopicForumView> topicForumList = topicForumService.listByForumId(appId, forumId, pageIndex, pageSize);
		
		List<TopicView> topicList = topicForumList.stream().map(topicForum -> findDetailByTopicIdAndMemberId(topicForum.getTopicId(), memberId)).collect(Collectors.toList());

		return topicList;
	}

	@Override
	public List<Topic> allTopicListByMemberId(Topic body) {
		//先从topic表中根据userId找到所有发过的topic
		List<Topic> topicList = listByMemberId(body.getAppId(), body.getMemberId(), body.getPageIndex(), body.getPageSize());

        for (Topic topic : topicList) {
        	String topicId = topic.getTopicId();

    		//取得topicId去话题图片表查询图片,所有图片放入list中(处理图片放在controller)
        	List<TopicMediaView> topicMedias = topicMediaService.listByTopicId(topicId);
        	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);

        	//取得topicId去话题论坛表查询,得到所有话题所在论坛
        	List<TopicForumView> allTopicForumList = topicForumService.listByTopicId(topicId);
        	//new一个list存放所有论坛对象,论坛对象存放,id和名称
        	ArrayList<Forum> forumList = new ArrayList<>();
        	//遍历allTopicForumList
        	for (TopicForumView aTopicForum : allTopicForumList) {
        		//得到论坛编号
				String forumId = aTopicForum.getForumId();
				//根据论坛编号去论坛信息表查询名称
				Forum forum = forumService.find(forumId, true);
				//把forum放入list
				forumList.add(forum);
			}
        	//把话题所在圈子放入topic
        	topic.put(Topic.TOPIC_FORUM_LIST, forumList);

        	//得到每个话题最新的3个评论,
        	List<TopicComment> commentList = topicCommentService.listForAdmin(body.getAppId(), null, topicId, null, null, null, 1, 3);
        	topic.put(Topic.TOPIC_COMMENT_LIST, commentList);

        	//取得topicId去话题收藏表查询,得到收藏数
        	String countBookMark = "" + topicUserBookmarkService.countForAdmin(body.getAppId(), topicId, null);
        	topic.put(Topic.TOPIC_COUNT_BOOKMARK, countBookMark);


        	//取得topicId去话题点赞表查询,得到点赞数
        	String countLike = "" + topicUserLikeService.countForAdmin(body.getAppId(), null, topicId);
        	topic.put(Topic.TOPIC_COUNT_LIKE, countLike);


        	//取得topicId去话题评论表查询,得到评论数
        	String countComment = "" + topicCommentService.countForAdmin(body.getAppId(), null, topicId, null, null, null);
        	topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
        	
        	
        	//是否被用户收藏,根据requestUserId和topicId去查询用户收藏关联表有没有记录,有就设置一个常量字段
        	TopicUserBookmarkView findTopicUserBookmark = topicUserBookmarkService.findByTopicIdAndMemberId(topicId, body.getMemberId());
        	if (findTopicUserBookmark != null) {
        		topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, true);
			}
        	
        	//是否被用户点赞
        	TopicUserLikeView findLike = topicUserLikeService.findByTopicIdAndMemberId(topicId, body.getMemberId());
        	if (findLike != null) {
        		topic.put(Topic.TOPIC_USER_IS_LIKE, true);
			}

        	//把封装好的topic放入list,然后返回
        	/**
        	 * 本来就是从topicList中取到的,应该不用再放回去了
        	 */
        	//topicList.add(topic);
		}

		return topicList;
	}

	@Override
	public TopicView findDetailByTopicIdAndMemberId(String topicId, String memberId) {
		//根据topicId查询topic
		TopicView topic = find(topicId);
		
		//TODO 检查topic是否为null
		// 话题多媒体列表
        // 论坛列表
        // 话题最新3条评论
        // 话题收藏数
        Integer countBookMark = topicUserBookmarkService.countByTopicId(topic.getTopicId());
        topic.put(Topic.TOPIC_COUNT_BOOKMARK, countBookMark);

        // 话题点赞数
        Integer countLike = topicUserLikeService.countByTopicId(topic.getTopicId());
        topic.put(Topic.TOPIC_COUNT_LIKE, countLike);

        // 话题评论数
        Integer countComment = topicCommentService.countByTopicId(topic.getTopicId());
        topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
        
        // 是否被用户收藏
        TopicUserBookmarkView topicUserBookmark = topicUserBookmarkService.findByTopicIdAndMemberId(topic.getTopicId(), memberId);
        topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(topicUserBookmark));
        
        // 是否被用户点赞
        TopicUserLikeView topicUserLike = topicUserLikeService.findByTopicIdAndMemberId(topic.getTopicId(), memberId);
        topic.put(Topic.TOPIC_USER_IS_LIKE, !Util.isNullOrEmpty(topicUserLike));
        
		return topic;
	}


	@Override
	public Integer countByMemberIdList(String appId, List<String> memberIdList) {
		Criteria criteria = Criteria.where(TopicView.APP_ID).is(appId)
                .and(TopicView.MEMBER_ID).in(memberIdList)
                .and(TopicView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
	}

	@Override
	public List<TopicView> listByMemberIdList(String appId, List<String> memberIdToSearchList, List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize) {
		Criteria criteria = Criteria.where(TopicView.APP_ID).is(appId)
                .and(TopicView.MEMBER_ID).in(memberIdToSearchList)
                .and(TopicView.TOPIC_ID).nin(excludeTopicIdList)
                .and(TopicView.SYSTEM_CREATE_TIME).lte(systemCreateTime)
                .and(TopicView.SYSTEM_STATUS).is(true);
		// DateUtil
        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicView> topicList = list(query, sort, pageIndex, pageSize);
        
        return topicList;
	}
	
	@Override
    public List<TopicView> listDetailByMemberIdList(String appId, String requestMemberId, List<String> memberIdToSearchList, List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize) {
        List<TopicView> topicList = listByMemberIdList(appId, memberIdToSearchList, excludeTopicIdList, systemCreateTime, pageIndex, pageSize);
        
        if (Util.isNullOrEmpty(topicList)) {
            return topicList;
        }
        
        for (TopicView topic : topicList) {
            topic.putAll(findDetailByTopicIdAndMemberId(topic.getTopicId(), requestMemberId));
        }

        return topicList;
    }
	
    @Override
    public Topic deleteByTopicId(String appId, String topicId, String systemRequestUserId, Integer systemVersion) {
    	Topic result = topicService.delete(topicId, systemRequestUserId, systemVersion);
    	boolean success = false; 
        if (result != null) {
            //删除话题论坛关联
            topicForumService.deleteByTopicId(topicId, appId, systemRequestUserId);

            //删除话题多媒体
            topicMediaService.deleteByTopicId(topicId, appId, systemRequestUserId);

            //删除话题评论
            topicCommentService.deleteByTopicId(topicId, appId, systemRequestUserId);

            //删除话题收藏
            topicUserBookmarkService.deleteByTopicId(topicId, appId, systemRequestUserId);

            //删除话题点赞 
            topicUserLikeService.deleteByTopicId(topicId, appId, systemRequestUserId);

            //删除取消收藏 
            topicUserUnbookmarkService.deleteByTopicId(topicId, appId, systemRequestUserId);

            //删除话题取消点赞
            topicUserUnlikeService.deleteByTopicId(topicId, appId, systemRequestUserId);
            
            success = true;
        }
        
        if (success) {
			return result;
		}else {
			return null;
		}
    }

	@Override
	public List<Topic> listByTopicIdList(List<String> topicIdList, Integer pageIndex, Integer pageSize) {
		List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .in(Topic.TOPIC_ID, topicIdList)
                        .eq(Topic.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
            );

		return topicList;
	}

	@Override
	public List<Topic> listDetailByTopicIdList(String memberId, List<String> topicIdList, Integer pageIndex,
			Integer pageSize) {
		List<Topic> topicList = listByTopicIdList(topicIdList, pageIndex, pageSize);
		
		if (Util.isNullOrEmpty(topicList)) {
            return new ArrayList<>();
        }
        
        for (Topic topic : topicList) {
            topic.putAll(findDetailByTopicIdAndMemberId(topic.getTopicId(), memberId));
        }

        return topicList;
		
	}

	@Override
	public Integer countTopicByMemberId(String memberId) {
		Integer count = count(
                new BaseWrapper<Topic>()
                        .eq(Topic.MEMBER_ID, memberId)
                        .eq(Topic.SYSTEM_STATUS, true)
        );
		return count;
	}

	@Override
	public Integer countTopicByMemberIdWithRedis(String appId, String memberId) {
		
		Criteria criteria = Criteria.where(TopicView.APP_ID).is(appId)
                .and(TopicView.MEMBER_ID).is(memberId)
                .and(TopicView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
	}

	@Override
	public Boolean saveWithRedis(Topic entity, String id, String systemCreateUserId) {
		
		return null;
	}

	@Override
	public List<TopicView> listDetailByTopicIdList(String memberId, List<String> topicIdList) {
		List<TopicView> topicList = listByTopicIdList(topicIdList);
		
		if (Util.isNullOrEmpty(topicList)) {
            return new ArrayList<>();
        }
        
        for (TopicView topic : topicList) {
            topic.putAll(findDetailByTopicIdAndMemberId(topic.getTopicId(), memberId));
        }

        return topicList;
	}

	@Override
	public List<TopicView> listByTopicIdList(List<String> topicIdList) {
		Criteria criteria = Criteria.where(TopicView.TOPIC_ID).in(topicIdList)
                .and(TopicView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();
        orders.add(new Order(Sort.Direction.DESC, TopicView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicView> topicList = list(query, sort);

        return topicList;
	}

	@Override
	public List<TopicView> hotTopicList(List<String> excludeTopicIdList, Date systemCreateTime, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(TopicView.TOPIC_ID).nin(excludeTopicIdList)
        		.and(TopicView.SYSTEM_CREATE_TIME).gte(DateUtil.getTwoMonthAgoDateTime()).lte(systemCreateTime)
                .and(TopicView.SYSTEM_STATUS).is(true);

        List<Order> orders = new ArrayList<Order>();

        orders.add(new Order(Sort.Direction.DESC, TopicView.TOPIC_COUNT_COMMENT));
        orders.add(new Order(Sort.Direction.DESC, TopicView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<TopicView> topicList = list(query, sort, pageIndex, pageSize);

        return topicList;
	}

	@Override
	public Integer countAllUserTopic() {
		Criteria criteria = Criteria.where(TopicView.SYSTEM_STATUS).is(true)
				.and(Topic.SYSTEM_CREATE_TIME).gte(DateUtil.getTwoMonthAgoDateTime());

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
	}
}