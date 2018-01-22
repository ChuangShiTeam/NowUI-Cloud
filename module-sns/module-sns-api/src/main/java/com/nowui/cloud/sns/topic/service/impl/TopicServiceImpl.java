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
import com.nowui.cloud.sns.topic.entity.TopicUserUnbookmark;
import com.nowui.cloud.sns.topic.entity.TopicUserUnlike;
import com.nowui.cloud.sns.topic.mapper.TopicMapper;
import com.nowui.cloud.sns.topic.service.TopicCommentService;
import com.nowui.cloud.sns.topic.service.TopicForumService;
import com.nowui.cloud.sns.topic.service.TopicMediaService;
import com.nowui.cloud.sns.topic.service.TopicService;
import com.nowui.cloud.sns.topic.service.TopicUserBookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserLikeService;
import com.nowui.cloud.sns.topic.service.TopicUserUnbookmarkService;
import com.nowui.cloud.sns.topic.service.TopicUserUnlikeService;
import com.nowui.cloud.util.Util;

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
	
	@Autowired
	private TopicUserUnbookmarkService topicUserUnbookmarkService;
	
	@Autowired
	private TopicUserUnlikeService topicUserUnlikeService;
	
    @Override
    public Integer countForAdmin(String appId, String topicSummary, String userId,  String topicLocation, Boolean topicIsLocation) {
        Integer count = count(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.TOPIC_SUMMARY, topicSummary)
                        .likeAllowEmpty(Topic.USER_ID, userId)
                        .likeAllowEmpty(Topic.TOPIC_LOCATION, topicLocation)
                        .eqAllowEmpty(Topic.TOPIC_IS_LOCATION, topicIsLocation)
                        .eq(Topic.SYSTEM_STATUS, true)
        );

        return count;
    }

    @Override
    public List<Topic> listForAdmin(String appId, String topicSummary, String userId, String latitude, String longtitude, String topicLocation, Boolean topicIsLocation, Boolean topicIsTop, Boolean topicIsRecommend, Integer topicTopLevel, Integer pageIndex, Integer pageSize) {
        List<Topic> topicList = list(
                new BaseWrapper<Topic>()
                        .eq(Topic.APP_ID, appId)
                        .likeAllowEmpty(Topic.TOPIC_SUMMARY, topicSummary)
                        .likeAllowEmpty(Topic.USER_ID, userId)
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
	public List<Topic> allTopicListByForumId(TopicForum body) {
		//根据forumId去话题关联表查找所有topic
		List<TopicForum> listForAdmin = topicForumService.listForAdmin(body.getAppId(), body.getForumId(), null, body.getPageIndex(), body.getPageSize());
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
        	List<TopicMedia> topicMedias = topicMediaService.listByTopicId(body.getAppId(), topicId);
        	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);

        	//取得topicId去话题论坛表查询,得到所有话题所在论坛
        	List<TopicForum> allTopicForumList = topicForumService.listByTopicId(body.getAppId(), topicId);
        	//new一个list存放所有论坛对象,论坛对象存放,id和名称
        	ArrayList<Forum> forumList = new ArrayList<>();
        	//遍历allTopicForumList
        	for (TopicForum aTopicForum : allTopicForumList) {
        		//得到论坛编号
				String forumId = aTopicForum.getForumId();
				//根据论坛编号去论坛信息表查询名称
				Forum forum = forumService.find(forumId, true);
				//把forum放入list
				forumList.add(forum);
			}
        	//把话题所在圈子放入topic
        	topic.put(Topic.TOPIC_FORUM_LIST, forumList);



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
        	List<TopicMedia> topicMedias = topicMediaService.listByTopicId(body.getAppId(), topicId);
        	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);

        	//取得topicId去话题论坛表查询,得到所有话题所在论坛
        	List<TopicForum> allTopicForumList = topicForumService.listByTopicId(body.getAppId(), topicId);
        	//new一个list存放所有论坛对象,论坛对象存放,id和名称
        	ArrayList<Forum> forumList = new ArrayList<>();
        	//遍历allTopicForumList
        	for (TopicForum aTopicForum : allTopicForumList) {
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
    	List<TopicMedia> topicMedias = topicMediaService.listByTopicId(body.getAppId(), topicId);
    	topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);
    	

    	
    	//取得topicId去话题论坛表查询,得到所有话题所在论坛
    	List<TopicForum> allTopicForumList = topicForumService.listByTopicId(body.getAppId(), topicId);
    	//new一个list存放所有论坛对象,论坛对象存放,id和名称
    	ArrayList<Forum> forumList = new ArrayList<>();
    	//遍历allTopicForumList
    	for (TopicForum aTopicForum : allTopicForumList) {
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
    	
    	//取得点赞的用户列表,显示3个
    	List<TopicUserLike> userLikeList = topicUserLikeService.listForAdmin(body.getAppId(), null, topicId, 1, 3);
    	topic.put(Topic.TOPIC_USER_LIKE_LIST, userLikeList);


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
    public List<Topic> listDetailByUserIdList(String appId, String userId, List<String> userIdList, Integer pageIndex, Integer pageSize) {
        List<Topic> topicList = listByUserIdList(appId, userIdList, pageIndex, pageSize);
        
        if (Util.isNullOrEmpty(topicList)) {
            return null;
        }
        
        for (Topic topic : topicList) {
            
            String topicId = topic.getTopicId();

            // 取得topicId去话题图片表查询图片,所有图片放入list中
            List<TopicMedia> topicMedias = topicMediaService.listByTopicId(appId, topicId);
            topic.put(Topic.TOPIC_MEDIA_LIST, topicMedias);

            // 取得topicId去话题论坛表查询, 得到所有话题所在论坛
            List<TopicForum> topicForumList = topicForumService.listByTopicId(appId, topicId);
            // new一个list存放所有论坛对象,论坛对象存放,id和名称
            ArrayList<Forum> forumList = new ArrayList<>();
            // 遍历allTopicForumList
            for (TopicForum topicForum : topicForumList) {
                // 得到论坛编号
                String forumId = topicForum.getForumId();
                // 根据论坛编号去论坛信息表查询名称
                Forum forum = forumService.find(forumId, true);
                // 把forum放入list
                forumList.add(forum);
            }
            topic.put(Topic.TOPIC_FORUM_LIST, forumList);

            // 得到每个话题最新的3个评论,
            List<TopicComment> commentList = topicCommentService.listByTopicId(appId, topicId, 1, 3);
            topic.put(Topic.TOPIC_COMMENT_LIST, commentList);

            // 取得topicId去话题收藏表查询,得到收藏数
            Integer countBookMark = topicUserBookmarkService.countByTopicId(appId, topicId);
            topic.put(Topic.TOPIC_COUNT_BOOKMARK, countBookMark);

            // 取得topicId去话题点赞表查询,得到点赞数
            Integer countLike = topicUserLikeService.countByTopicId(appId, topicId);
            topic.put(Topic.TOPIC_COUNT_LIKE, countLike);

            // 取得topicId去话题评论表查询,得到评论数
            Integer countComment = topicCommentService.countByTopicId(appId, topicId);
            topic.put(Topic.TOPIC_COUNT_COMMENT, countComment);
            
            // 是否被用户收藏,根据requestUserId和topicId去查询用户收藏关联表有没有记录,有就设置一个常量字段
            TopicUserBookmark topicUserBookmark = topicUserBookmarkService.findTopicUserBookmark(appId, topicId, userId);
            topic.put(Topic.TOPIC_USER_IS_BOOKEMARK, !Util.isNullOrEmpty(topicUserBookmark));
            
            // 是否被用户点赞
            TopicUserLike topicUserLike = topicUserLikeService.findLike(appId, userId, topicId);
            topic.put(Topic.TOPIC_USER_IS_LIKE, topicUserLike);

        }

        return topicList;
    }

    @Override
    public Boolean deleteByTopicId(String appId, String topicId, String systemRequestUserId, Integer systemVersion) {
        Boolean result = topicService.delete(topicId, systemRequestUserId, systemVersion);

        if (result) {
          //删除话题论坛关联
            List<TopicForum> allTopicForumList = topicForumService.listByTopicId(appId, topicId);
            for (TopicForum topicForum : allTopicForumList) {
                topicForumService.delete(topicForum.getTopicForumId(), systemRequestUserId, topicForum.getSystemVersion());
            }

            //删除话题图片
            List<TopicMedia> listAllMediaByTopicId = topicMediaService.listByTopicId(appId, topicId);
            for (TopicMedia topicMedia : listAllMediaByTopicId) {
                topicMediaService.delete(topicMedia.getTopicMediaId(), systemRequestUserId, topicMedia.getSystemVersion());
            }

            //删除话题评论
            List<TopicComment> allCommentList = topicCommentService.listByTopicId(appId, topicId);
            for (TopicComment topicComment : allCommentList) {
                topicCommentService.delete(topicComment.getTopicCommentId(), systemRequestUserId, topicComment.getSystemVersion());
            }

            //删除话题收藏
            List<TopicUserBookmark> allListByTopicId = topicUserBookmarkService.listByTopicId(appId, topicId);
            for (TopicUserBookmark topicUserBookmark : allListByTopicId) {
                topicUserBookmarkService.delete(topicUserBookmark.getTopicUserBookmarkId(), systemRequestUserId, topicUserBookmark.getSystemVersion());
            }

            //删除话题点赞 
            List<TopicUserLike> allLikeListByTopic = topicUserLikeService.listByTopicId(appId, topicId);
            for (TopicUserLike topicUserLike : allLikeListByTopic) {
                topicUserLikeService.delete(topicUserLike.getTopicUserLikeId(), systemRequestUserId, topicUserLike.getSystemVersion());
            }

            //删除取消收藏 
            List<TopicUserUnbookmark> allUnBookMarkListByTopic = topicUserUnbookmarkService.allUnBookMarkListByTopic(appId, topicId);
            for (TopicUserUnbookmark topicUserUnbookmark : allUnBookMarkListByTopic) {
                topicUserUnbookmarkService.delete(topicUserUnbookmark.getTopicUserUnbookmarkId(), systemRequestUserId, topicUserUnbookmark.getSystemVersion());
            }

            //删除话题取消点赞
            List<TopicUserUnlike> unlikeList = topicUserUnlikeService.allUnlikeListByTopicId(appId, topicId);
            for (TopicUserUnlike topicUserUnlike : unlikeList) {
                topicUserUnlikeService.delete(topicUserUnlike.getTopicUserUnlikeId(), systemRequestUserId, topicUserUnlike.getSystemVersion());
            }
        }
        
        return result;
    }

}