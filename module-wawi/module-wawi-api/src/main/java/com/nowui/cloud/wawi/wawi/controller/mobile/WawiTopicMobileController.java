package com.nowui.cloud.wawi.wawi.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.member.member.rpc.MemberRpc;
import com.nowui.cloud.sns.member.rpc.MemberFollowRpc;
import com.nowui.cloud.sns.topic.rpc.TopicRpc;

import io.swagger.annotations.Api;

/**
 * 哇伊话题移动端控制器
 * 
 * @author marcus
 *
 * 2018年1月26日
 */
@Api(value = "哇伊话题", description = "哇伊话题移动端接口管理")
@RestController
public class WawiTopicMobileController extends BaseController {
    
    @Autowired
    private MemberFollowRpc memberFollowRpc;
    
    @Autowired
    private TopicRpc topicRpc;
    
    @Autowired
    private MemberRpc memberRpc;
    
    /*@ApiOperation(value = "哇伊话题首页初始数据")
    @RequestMapping(value = "/wawi/topic/mobile/v1/home/list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> homeListV1() {
        Topic topic = getEntry(Topic.class);
        validateRequest(
                topic,
                Topic.APP_ID,
                Topic.PAGE_INDEX,
                Topic.PAGE_SIZE,
                Topic.SYSTEM_REQUEST_USER_ID
        );
       
        // 用户关注的人的编号列表
        List<String> followUserIdList = memberFollowRpc.followUserIdList(topic.getSystemRequestUserId());
        // 加上本人的用户编号
        followUserIdList.add(topic.getSystemRequestUserId());
        
        Integer countResult = topicRpc.countByUserIdList(topic.getAppId(), followUserIdList);
        List<Topic> resultList = topicRpc.listDetailByUserIdList(topic.getAppId(), topic.getSystemRequestUserId(), followUserIdList, topic.getPageIndex(), topic.getPageSize());
        
        // 在controller层调用其他接口处理发布话题者信息(昵称,头像,是否关注)
        String userIds = Util.beanToFieldString(resultList, Topic.USER_ID);
        List<Member> nickAndAvatarAndIsFollowList = memberRpc.nickNameAndAvatarAndIsFollowListV1(userIds, topic.getSystemRequestUserId());
        
        resultList = Util.beanAddField(
                resultList, 
                Topic.USER_ID, 
                User.USER_ID, 
                nickAndAvatarAndIsFollowList, 
                User.USER_ID,
                UserAvatar.USER_AVATAR,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW
        );
        
        
        validateResponse(
                Topic.TOPIC_ID,
                Topic.TOPIC_SUMMARY,
                Topic.USER_ID,
                Topic.LATITUDE,
                Topic.LONGTITUDE,
                Topic.TOPIC_LOCATION,
                Topic.TOPIC_IS_LOCATION,
                Topic.TOPIC_IS_TOP,
                Topic.TOPIC_TOP_LEVEL,
                Topic.TOPIC_FORUM_LIST,
                Topic.TOPIC_MEDIA_LIST,
                Topic.TOPIC_COMMENT_LIST,
                Topic.TOPIC_COUNT_BOOKMARK,
                Topic.TOPIC_COUNT_LIKE,
                Topic.TOPIC_COUNT_COMMENT,
                Topic.TOPIC_USER_IS_BOOKEMARK,
                Topic.TOPIC_USER_IS_LIKE,
                Topic.TOPIC_USER_LIKE_LIST,
                
                User.USER_ID,
                UserAvatar.USER_AVATAR,
                UserNickName.USER_NICK_NAME,
                MemberFollow.MEMBER_IS_FOLLOW,
                BaseEntity.SYSTEM_CREATE_TIME
        );

        return renderJson(countResult, resultList);
    }
*/
}
