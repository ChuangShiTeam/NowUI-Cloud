package com.nowui.cloud.wawi.wawi.controller.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.base.app.entity.App;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.cms.advertisement.entity.Advertisement;
import com.nowui.cloud.cms.article.entity.Article;
import com.nowui.cloud.cms.navigation.entity.Navigation;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.sns.forum.rpc.ForumRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 哇伊圈子移动端控制器
 * 
 * @author marcus
 *
 * 2018年1月26日
 */
@Api(value = "哇伊圈子", description = "哇伊圈子移动端接口管理")
@RestController
public class WawiForumMobileController extends BaseController {
    
    @Autowired
    private ForumRpc forumRpc;
    
    @ApiOperation(value = "哇伊圈子首页初始数据")
    @RequestMapping(value = "/wawi/mobile/v1/index/init", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> indexInitV1() {
        App app = getEntry(App.class);
        validateRequest(
            app, 
            App.APP_ID,
            App.PAGE_INDEX,
            App.PAGE_SIZE
        );
        
        // 我加入圈子列表
        
        // 我感兴趣列表
        
        // 热门话题列表
        
        validateResponse(
                "forumJoinList", 
                "forumRecommendList",
                "hotTopicList"
        );
        return renderJson(true);
    }

}
