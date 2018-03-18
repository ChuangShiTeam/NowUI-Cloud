package com.nowui.cloud.member.member.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.member.member.entity.MemberDefaultAvatar;
import com.nowui.cloud.member.member.service.MemberDefaultAvatarService;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.view.CommonView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 会员默认头像管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
@Api(value = "会员默认头像", description = "会员默认头像管理端接口管理")
@RestController
public class MemberDefaultAvatarAdminController extends BaseController {

    @Autowired
    private MemberDefaultAvatarService memberDefaultAvatarService;
    
    @Autowired
    private FileRpc fileRpc;

    @ApiOperation(value = "会员默认头像列表", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDefaultAvatarView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.PAGE_INDEX, value = "分页页数", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.PAGE_SIZE, value = "每页数量", required = true, paramType = "query", dataType = "int"),
    })
    @RequestMapping(value = "/member/default/avatar/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> listV1(@ApiIgnore MemberDefaultAvatarView memberDefaultAvatarView, @ApiIgnore CommonView commonView) {
        validateRequest(
                memberDefaultAvatarView,
                MemberDefaultAvatarView.APP_ID
        );
        
        validateRequest(
                commonView,
                CommonView.PAGE_INDEX,
                CommonView.PAGE_SIZE
        );

        Integer resultTotal = memberDefaultAvatarService.countForAdmin(memberDefaultAvatarView.getAppId());
        List<MemberDefaultAvatarView> resultList = memberDefaultAvatarService.listForAdmin(memberDefaultAvatarView.getAppId(), commonView.getPageIndex(), commonView.getPageSize());

        validateResponse(
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_ID,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_PATH
        );

        return renderJson(resultTotal, resultList);
    }

    @ApiOperation(value = "会员默认头像信息", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDefaultAvatarView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID, value = "会员头像编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/default/avatar/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> findV1(@ApiIgnore MemberDefaultAvatarView memberDefaultAvatarView) {
        validateRequest(
                memberDefaultAvatarView,
                MemberDefaultAvatarView.APP_ID,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID
        );

        MemberDefaultAvatarView result = memberDefaultAvatarService.find(memberDefaultAvatarView.getMemberDefaultAvatarId());

        validateResponse(
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID,
            	MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_ID,
            	MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_PATH,
                MemberDefaultAvatarView.SYSTEM_VERSION
        );

        return renderJson(result);
    }

    @ApiOperation(value = "会员默认头像新增", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDefaultAvatarView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_ID, value = "会员默认头像编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_PATH, value = "会员默认头像路径", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/default/avatar/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> saveV1(@ApiIgnore MemberDefaultAvatar memberDefaultAvatar, @ApiIgnore MemberDefaultAvatarView memberDefaultAvatarView, @ApiIgnore CommonView commonView) {
        validateRequest(
                memberDefaultAvatarView,
                MemberDefaultAvatarView.APP_ID,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_ID,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_PATH
        );
        
        String memberDefaultAvatarId = Util.getRandomUUID();

        MemberDefaultAvatar result = memberDefaultAvatarService.save(memberDefaultAvatar, memberDefaultAvatarId, commonView.getSystemRequestUserId());

        Boolean success = false;

        if (result != null) {
            memberDefaultAvatarView.copy(memberDefaultAvatar);
            
            memberDefaultAvatarService.save(memberDefaultAvatarView);
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "会员默认头像修改", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDefaultAvatarView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID, value = "会员默认头像编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_ID, value = "会员默认头像编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_PATH, value = "会员默认头像路径", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.SYSTEM_VERSION, value = "会员默认头像路径", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/default/avatar/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> updateV1(@ApiIgnore MemberDefaultAvatar memberDefaultAvatar, @ApiIgnore MemberDefaultAvatarView memberDefaultAvatarView, @ApiIgnore CommonView commonView) {

        validateRequest(
                memberDefaultAvatarView,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID,
                MemberDefaultAvatarView.APP_ID,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_PATH,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_FILE_ID,
                MemberDefaultAvatarView.SYSTEM_VERSION
        );
        
        validateRequest(
                commonView,
                CommonView.SYSTEM_REQUEST_USER_ID
        );

        MemberDefaultAvatar result = memberDefaultAvatarService.update(memberDefaultAvatar, memberDefaultAvatar.getMemberDefaultAvatarId(), memberDefaultAvatar.getAppId(), commonView.getSystemRequestUserId(), memberDefaultAvatar.getSystemVersion());

        if (result != null) {
            memberDefaultAvatarView.copy(memberDefaultAvatar);
            
            memberDefaultAvatarService.update(memberDefaultAvatarView, memberDefaultAvatarView.getMemberDefaultAvatarId());
        }

        return renderJson(true);
    }

    @ApiOperation(value = "会员默认头像删除", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = MemberDefaultAvatarView.APP_ID, value = "应用编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID, value = "会员默认头像编号", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name = MemberDefaultAvatarView.SYSTEM_VERSION, value = "会员默认头像路径", required = true, paramType = "query", dataType = "int"),
        @ApiImplicitParam(name = CommonView.SYSTEM_REQUEST_USER_ID, value = "请求用户编号", required = true, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/member/default/avatar/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> deleteV1(@ApiIgnore MemberDefaultAvatarView memberDefaultAvatarView, @ApiIgnore CommonView commonView) {
        validateRequest(
                memberDefaultAvatarView,
                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID,
                MemberDefaultAvatarView.APP_ID,
                MemberDefaultAvatarView.SYSTEM_VERSION
        );

        MemberDefaultAvatar result = memberDefaultAvatarService.delete(memberDefaultAvatarView.getMemberDefaultAvatarId(), memberDefaultAvatarView.getAppId(), commonView.getSystemRequestUserId(), memberDefaultAvatarView.getSystemVersion());

        Boolean success = false;

        if (result != null) {
            memberDefaultAvatarView.copy(result);
            
            memberDefaultAvatarService.update(memberDefaultAvatarView, memberDefaultAvatarView.getMemberDefaultAvatarId());
            
            success = true;
        }

        return renderJson(success);
    }

    @ApiOperation(value = "会员默认头像数据同步", httpMethod = "POST")
    @RequestMapping(value = "/member/default/avatar/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> synchronizeV1() {
        List<MemberDefaultAvatar> memberDefaultAvatarList = memberDefaultAvatarService.listByMysql();

        for (MemberDefaultAvatar memberDefaultAvatar : memberDefaultAvatarList) {
            MemberDefaultAvatarView memberDefaultAvatarView = new MemberDefaultAvatarView();
            memberDefaultAvatarView.copy(memberDefaultAvatar);
            
            File file = fileRpc.findByMysqlV1(memberDefaultAvatar.getMemberDefaultAvatarFileId());

            memberDefaultAvatarView.setMemberDefaultAvatarFilePath(file.getFilePath());
            memberDefaultAvatarService.saveOrUpdate(memberDefaultAvatarView, memberDefaultAvatarView.getMemberDefaultAvatarId());
        }

        return renderJson(true);
    }

}