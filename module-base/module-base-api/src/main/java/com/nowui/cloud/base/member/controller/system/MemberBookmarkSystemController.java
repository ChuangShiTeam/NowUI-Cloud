package com.nowui.cloud.base.member.controller.system;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.base.member.entity.MemberBookmark;
import com.nowui.cloud.base.member.rpc.MemberBookmarkRpc;
import com.nowui.cloud.base.member.service.MemberBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员收藏系统端控制器
 *
 * @author marcus
 *
 * 2018-01-02
 */
@Api(value = "会员收藏", description = "会员收藏系统端接口管理")
@RestController
public class MemberBookmarkSystemController implements MemberBookmarkRpc {

}