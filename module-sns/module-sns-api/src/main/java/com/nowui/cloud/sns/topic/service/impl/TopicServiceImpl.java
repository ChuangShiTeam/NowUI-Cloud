package com.nowui.cloud.sns.topic.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.sns.forum.entity.Forum;
import com.nowui.cloud.sns.forum.service.ForumService;
import com.nowui.cloud.sns.topic.entity.Topic;
import com.nowui.cloud.sns.topic.entity.TopicComment;
import com.nowui.cloud.sns.topic.entity.TopicForum;
import com.nowui.cloud.sns.topic.entity.TopicMedia;
import com.nowui.cloud.sns.topic.entity.TopicUserBookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserLike;
import com.nowui.cloud.sns.topic.mapper.TopicMapper;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;

/**
 * 话题信息业务实现
 *
 * @author xupengfei
 *
 * 2018-01-08
 */
@Service
public class TopicServiceImpl extends BaseServiceImpl<TopicMapper, Topic> implements TopicService {
	
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
	
	
    @Override
    public Integer countForAdmin(String appId, String topicForumId, String topicSummary, String userId,  String topicLocation, Boolean topicIsLocation) {
        Integer count = count(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.TOPIC_FORUM_ID, topicForumId)
                        .likeAllowEmpty(Topic.TOPIC_SUMMARY, topicSummary)
                        .likeAllowEmpty(Topic.USER_ID, userId)
                        .likeAllowEmpty(Topic.TOPIC_LOCATION, topicLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_LOCATION, topicIsLocation)
                        .eq(Topic.SYSTEM_STATUS, true)
        );

        return count;
    }

    @Override
    public List<Topic> listForAdmin(String appId, String topicForumId, String topicSummary, String userId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecommend, Integer topTopLevel, Integer pageIndex, Integer pageSize) {
        List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.TOPIC_FORUM_ID, topicForumId)
                        .likeAllowEmpty(Topic.TOPIC_SUMMARY, topicSummary)
                        .likeAllowEmpty(Topic.USER_ID, userId)
                        .likeAllowEmpty(Topic.LATITUDE, latitude)
                        .likeAllowEmpty(Topic.LONGTITUDE, longtitude)
                        .likeAllowEmpty(Topic.TOPIC_LOCATION, topicLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_LOCATION, topicIsLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_TOP, topicIsTop)
                        .eqAllowEmpty(Topic.TOPIC_IS_RECOMAND, topicIsRecommend)
                        .eqAllowEmpty(Topic.TOP_TOP_LEVEL, topTopLevel)
                        .eq(Topic.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );
//        List<String> userIdList = 
//        List<Topic> topicList1 = list(
//        		new BaseWrapper<Topic>()
//        			.eq(Topic.APP_ID, appId)
//                    .in(Topic.USER_ID, userIdList)
//                    .eq(Topic.SYSTEM_STATUS, true)
//                    .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
//                pageIndex,
//                pageSize);
        return topicList;
    }
    
    
    @Override
	public List<Topic> listByUserId(String appId, String userId, Integer pageIndex, Integer pageSize) {
    	List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.USER_ID, userId)
                        .eq(Topic.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return topicList;
	}



	@Override
	public List<Topic> allTopicListByForumId(Topic body) {
		//根据forumId去话题关联表查找所有topic
		List<TopicForum> listForAdmin = topicForumService.listForAdmin(body.getAppId(), body.getString(Topic.FORUM_ID), null, body.getPageIndex(), body.getPageSize());
		List<Topic> topicList = new ArrayList<>();

        for (TopicForum topicForum : listForAdmin) {
        	String topicId = topicForum.getTopicId();


        	//取得topicId去topic表查询,得到topic对象
        	/**
        	 * 话题地点,话题内容,发布时间也包含了
        	 */
        	Topic topic = topicService.find(topicId, true);



        	//根据userId调用用户接口得到发布话题者 的 user对象(包含:昵称,头像,id),然后放入topic对象
        	//由controller来处理



    		//取得topicId去话题图片表查询图片,所有图片放入list中(处理图片放在controller)
        	List<TopicMedia> topicMedias = topicMediaService.listAllMediaByTopicId(body.getAppId(), topicId, null, null);
        	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);

        	//取得topicId去话题论坛表查询,得到所有话题所在论坛
        	List<TopicForum> allTopicForumList = topicForumService.allTopicForumList(body.getAppId(), null, topicId);
        	//new一个list存放所有论坛对象,论坛对象存放,id和名称
        	ArrayList<Forum> forumList = new ArrayList<>();
        	//遍历allTopicForumList
        	for (TopicForum aTopicForum : allTopicForumList) {
        		//得到论坛id
				String forumId = aTopicForum.getForumId();
				//根据论坛id去论坛信息表查询名称
				Forum forum = forumService.find(forumId, true);
				//把forum放入list
				forumList.add(forum);
			}
        	//把话题所在圈子放入topic
        	topic.put(Topic.TOPIC_FORUM_LIST, forumList);



        	//取得topicId去话题收藏表查询,得到收藏数
        	String countBookMark = "" + topicUserBookmarkService.countForAdmin(body.getAppId(), topicId, null);
        	topic.put(Topic.TOPIC_COUNT_BOOK_MARK, countBookMark);



        	//取得topicId去话题点赞表查询,得到点赞数
        	String countLike = "" + topicUserLikeService.countForAdmin(body.getAppId(), null, topicId);
        	topic.put(Topic.TOPIC_COUNT_LIKE, countLike);



        	//取得topicId去话题评论表查询,得到评论数
        	String countComment = "" + topicCommentService.countForAdmin(body.getAppId(), null, topicId, null, null, null);
        	topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
        	
        	
        	
        	//是否被用户收藏,根据requestUserId和topicId去查询用户收藏关联表有没有记录,有就设置一个常量字段
        	TopicUserBookmark findTopicUserBookmark = topicUserBookmarkService.findTopicUserBookmark(body.getAppId(), topicId, body.getSystemRequestUserId());
        	if (findTopicUserBookmark != null) {
        		topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, true);
			}
        	
        	
        	
        	//是否被用户点赞
        	TopicUserLike findLike = topicUserLikeService.findLike(body.getAppId(), body.getSystemRequestUserId(), topicId);
        	if (findLike != null) {
        		topic.put(Topic.TOPIC_USER_IS_LIKE, true);
			}
        	
        	

        	//把封装好的topic放入list,然后返回
        	topicList.add(topic);
		}

		return topicList;
	}

	@Override
	public List<Topic> allTopicListByUserId(Topic body) {
		//先从topic表中根据userId找到所有发过的topic
		List<Topic> topicList = listByUserId(body.getAppId(), body.getUserId(), body.getPageIndex(), body.getPageSize());

        for (Topic topic : topicList) {
        	String topicId = topic.getTopicId();


        	//根据userId调用用户接口得到发布话题者 的 user对象(包含:昵称,头像,id),然后放入topic对象
        	//不需要在本接口处理用户信息


    		//取得topicId去话题图片表查询图片,所有图片放入list中(处理图片放在controller)
        	List<TopicMedia> topicMedias = topicMediaService.listAllMediaByTopicId(body.getAppId(), topicId, null, null);
        	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);

        	//取得topicId去话题论坛表查询,得到所有话题所在论坛
        	List<TopicForum> allTopicForumList = topicForumService.allTopicForumList(body.getAppId(), null, topicId);
        	//new一个list存放所有论坛对象,论坛对象存放,id和名称
        	ArrayList<Forum> forumList = new ArrayList<>();
        	//遍历allTopicForumList
        	for (TopicForum aTopicForum : allTopicForumList) {
        		//得到论坛id
				String forumId = aTopicForum.getForumId();
				//根据论坛id去论坛信息表查询名称
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
        	topic.put(Topic.TOPIC_COUNT_BOOK_MARK, countBookMark);



        	//取得topicId去话题点赞表查询,得到点赞数
        	String countLike = "" + topicUserLikeService.countForAdmin(body.getAppId(), null, topicId);
        	topic.put(Topic.TOPIC_COUNT_LIKE, countLike);



        	//取得topicId去话题评论表查询,得到评论数
        	String countComment = "" + topicCommentService.countForAdmin(body.getAppId(), null, topicId, null, null, null);
        	topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
        	
        	
        	
        	//是否被用户收藏,根据requestUserId和topicId去查询用户收藏关联表有没有记录,有就设置一个常量字段
        	TopicUserBookmark findTopicUserBookmark = topicUserBookmarkService.findTopicUserBookmark(body.getAppId(), topicId, body.getSystemRequestUserId());
        	if (findTopicUserBookmark != null) {
        		topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, true);
			}
        	
        	
        	
        	//是否被用户点赞
        	TopicUserLike findLike = topicUserLikeService.findLike(body.getAppId(), body.getSystemRequestUserId(), topicId);
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
	public Topic findTheTopicDetails(Topic body) {
		

		//根据topicId查询topic
		Topic topic = find(body.getTopicId());
		
        String topicId = topic.getTopicId();

        //处理用户信息,由controller处理
        

		//取得topicId去话题图片表查询图片,所有图片放入list中(处理图片放在controller)
    	List<TopicMedia> topicMedias = topicMediaService.listAllMediaByTopicId(body.getAppId(), topicId, null, null);
    	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);
    	

    	
    	//取得topicId去话题论坛表查询,得到所有话题所在论坛
    	List<TopicForum> allTopicForumList = topicForumService.allTopicForumList(body.getAppId(), null, topicId);
    	//new一个list存放所有论坛对象,论坛对象存放,id和名称
    	ArrayList<Forum> forumList = new ArrayList<>();
    	//遍历allTopicForumList
    	for (TopicForum aTopicForum : allTopicForumList) {
    		//得到论坛id
			String forumId = aTopicForum.getForumId();
			//根据论坛id去论坛信息表查询名称
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
    	topic.put(Topic.TOPIC_COUNT_BOOK_MARK, countBookMark);



    	//取得topicId去话题点赞表查询,得到点赞数
    	String countLike = "" + topicUserLikeService.countForAdmin(body.getAppId(), null, topicId);
    	topic.put(Topic.TOPIC_COUNT_LIKE, countLike);



    	//取得topicId去话题评论表查询,得到评论数
    	String countComment = "" + topicCommentService.countForAdmin(body.getAppId(), null, topicId, null, null, null);
    	topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
    	
    	
    	
    	//是否被用户收藏,根据requestUserId和topicId去查询用户收藏关联表有没有记录,有就设置一个常量字段,并设置为true
    	TopicUserBookmark findTopicUserBookmark = topicUserBookmarkService.findTopicUserBookmark(body.getAppId(), topicId, body.getSystemRequestUserId());
    	if (findTopicUserBookmark != null) {
    		topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, true);
		}
    	
    	
    	
    	//是否被用户点赞
    	TopicUserLike findLike = topicUserLikeService.findLike(body.getAppId(), body.getSystemRequestUserId(), topicId);
    	if (findLike != null) {
    		topic.put(Topic.TOPIC_USER_IS_LIKE, true);
		}
    	

		return topic;
	}


	@Override
	public Integer countByUserIdList(String appId, List<String> userIdList) {
		Integer count = count(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .in(Topic.USER_ID, userIdList)
                        .eq(Topic.SYSTEM_STATUS, true)
        );

		return count;
	}

	@Override
	public List<Topic> listByUserIdList(String appId, List<String> userIdList, Integer pageIndex, Integer pageSize) {
		List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .in(Topic.USER_ID, userIdList)
                        .eq(Topic.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Topic.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
            );

		return topicList;
	}

	@Override
	public List<Topic> handleTopic(Topic body, List<Topic> topicList) {

		for (Topic topic : topicList) {
        	String topicId = topic.getTopicId();


        	//根据userId调用用户接口得到发布话题者 的 user对象(包含:昵称,头像,id),然后放入topic对象
        	//由controller处理



    		//取得topicId去话题图片表查询图片,所有图片放入list中(处理图片放在controller)
        	List<TopicMedia> topicMedias = topicMediaService.listAllMediaByTopicId(body.getAppId(), topicId, null, null);
        	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);
        	

        	//取得topicId去话题论坛表查询,得到所有话题所在论坛
        	List<TopicForum> allTopicForumList = topicForumService.allTopicForumList(body.getAppId(), null, topicId);
        	//new一个list存放所有论坛对象,论坛对象存放,id和名称
        	ArrayList<Forum> forumList = new ArrayList<>();
        	//遍历allTopicForumList
        	for (TopicForum aTopicForum : allTopicForumList) {
        		//得到论坛id
				String forumId = aTopicForum.getForumId();
				//根据论坛id去论坛信息表查询名称
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
        	topic.put(Topic.TOPIC_COUNT_BOOK_MARK, countBookMark);



        	//取得topicId去话题点赞表查询,得到点赞数
        	String countLike = "" + topicUserLikeService.countForAdmin(body.getAppId(), null, topicId);
        	topic.put(Topic.TOPIC_COUNT_LIKE, countLike);



        	//取得topicId去话题评论表查询,得到评论数
        	String countComment = "" + topicCommentService.countForAdmin(body.getAppId(), null, topicId, null, null, null);
        	topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
        	
        	
        	
        	//是否被用户收藏,根据requestUserId和topicId去查询用户收藏关联表有没有记录,有就设置一个常量字段
        	TopicUserBookmark findTopicUserBookmark = topicUserBookmarkService.findTopicUserBookmark(body.getAppId(), topicId, body.getSystemRequestUserId());
        	if (findTopicUserBookmark != null) {
        		topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, true);
			}
        	
        	
        	
        	//是否被用户点赞
        	TopicUserLike findLike = topicUserLikeService.findLike(body.getAppId(), body.getSystemRequestUserId(), topicId);
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
	
	


	




}