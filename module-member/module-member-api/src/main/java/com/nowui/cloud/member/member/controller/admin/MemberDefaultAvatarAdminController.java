package com.nowui.cloud.member.member.controller.admin;

import com.alibaba.fastjson.JSON;
import com.nowui.cloud.controller.BaseController;
import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.rpc.FileRpc;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.member.member.entity.MemberDefaultAvatar;
import com.nowui.cloud.member.member.view.MemberDefaultAvatarView;
import com.nowui.cloud.member.member.service.MemberDefaultAvatarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户默认头像管理端控制器
 *
 * @author xupengfei
 *
 * 2018-03-05
 */
@Api(value = "用户默认头像", description = "用户默认头像管理端接口管理")
@RestController
public class MemberDefaultAvatarAdminController extends BaseController {

//    @Autowired
//    private MemberDefaultAvatarService memberDefaultAvatarService;
//    
//    @Autowired
//    private FileRpc fileRpc;
//
//    @ApiOperation(value = "用户默认头像列表")
//    @RequestMapping(value = "/member/default/avatar/admin/v1/list", method = {RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> listV1() {
//        MemberDefaultAvatarView memberDefaultAvatarView = getEntry(MemberDefaultAvatarView.class);
//
//        validateRequest(
//                memberDefaultAvatarView,
//                MemberDefaultAvatarView.APP_ID,
//                MemberDefaultAvatarView.USER_AVATAR_FILE_ID,
//                MemberDefaultAvatarView.PAGE_INDEX,
//                MemberDefaultAvatarView.PAGE_SIZE
//        );
//
//        Integer resultTotal = memberDefaultAvatarService.countForAdmin(memberDefaultAvatarView.getAppId(), memberDefaultAvatarView.getUserAvatarFileId());
//        List<MemberDefaultAvatarView> resultList = memberDefaultAvatarService.listForAdmin(memberDefaultAvatarView.getAppId(), memberDefaultAvatarView.getUserAvatarFileId(), memberDefaultAvatarView.getPageIndex(), memberDefaultAvatarView.getPageSize());
//
//        validateResponse(
//                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID,
//                MemberDefaultAvatarView.USER_AVATAR_FILE_ID,
//                MemberDefaultAvatarView.USER_AVATAR_FILE_PATH
//        );
//
//        return renderJson(resultTotal, resultList);
//    }
//
//    @ApiOperation(value = "用户默认头像信息")
//    @RequestMapping(value = "/member/default/avatar/admin/v1/find", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> findV1() {
//        MemberDefaultAvatarView memberDefaultAvatarView = getEntry(MemberDefaultAvatarView.class);
//
//        validateRequest(
//                memberDefaultAvatarView,
//                MemberDefaultAvatarView.APP_ID,
//                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID
//        );
//
//        MemberDefaultAvatarView result = memberDefaultAvatarService.find(memberDefaultAvatarView.getMemberDefaultAvatarId());
//
//        validateResponse(
//                MemberDefaultAvatarView.MEMBER_DEFAULT_AVATAR_ID,
//            	MemberDefaultAvatarView.USER_AVATAR_FILE_ID,
//                MemberDefaultAvatarView.SYSTEM_VERSION,
//                MemberDefaultAvatarView.USER_AVATAR_FILE_PATH
//        );
//
//        return renderJson(result);
//    }
//
//    @ApiOperation(value = "用户默认头像新增")
//    @RequestMapping(value = "/member/default/avatar/admin/v1/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> saveV1() {
//        MemberDefaultAvatar memberDefaultAvatarEntity = getEntry(MemberDefaultAvatar.class);
//
//        String memberDefaultAvatarId = Util.getRandomUUID();
//
//        validateRequest(
//                memberDefaultAvatarEntity,
//                MemberDefaultAvatar.APP_ID,
//                MemberDefaultAvatar.USER_AVATAR_FILE_ID,
//                MemberDefaultAvatar.USER_AVATAR_FILE_PATH
//        );
//
//        MemberDefaultAvatar result = memberDefaultAvatarService.save(memberDefaultAvatarEntity, memberDefaultAvatarId, memberDefaultAvatarEntity.getSystemRequestUserId());
//
//        Boolean success = false;
//
//        if (result != null) {
//            MemberDefaultAvatarView memberDefaultAvatarView = JSON.parseObject(result.toJSONString(), MemberDefaultAvatarView.class);
//            memberDefaultAvatarService.save(memberDefaultAvatarView);
//            
//            success = true;
//        }
//
//        return renderJson(success);
//    }
//
//    @ApiOperation(value = "用户默认头像修改")
//    @RequestMapping(value = "/member/default/avatar/admin/v1/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> updateV1() {
//        MemberDefaultAvatar memberDefaultAvatarEntity = getEntry(MemberDefaultAvatar.class);
//
//        validateRequest(
//                memberDefaultAvatarEntity,
//                MemberDefaultAvatar.MEMBER_DEFAULT_AVATAR_ID,
//                MemberDefaultAvatar.APP_ID,
//                MemberDefaultAvatar.USER_AVATAR_FILE_ID,
//                MemberDefaultAvatar.SYSTEM_VERSION,
//                MemberDefaultAvatar.USER_AVATAR_FILE_PATH
//        );
//
//        MemberDefaultAvatar result = memberDefaultAvatarService.update(memberDefaultAvatarEntity, memberDefaultAvatarEntity.getMemberDefaultAvatarId(), memberDefaultAvatarEntity.getSystemRequestUserId(), memberDefaultAvatarEntity.getSystemVersion());
//
//        if (result != null) {
//            MemberDefaultAvatarView memberDefaultAvatarView = JSON.parseObject(result.toJSONString(), MemberDefaultAvatarView.class);
//            memberDefaultAvatarService.update(memberDefaultAvatarView);
//        }
//
//        return renderJson(true);
//    }
//
//    @ApiOperation(value = "用户默认头像删除")
//    @RequestMapping(value = "/member/default/avatar/admin/v1/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> deleteV1() {
//        MemberDefaultAvatar memberDefaultAvatarEntity = getEntry(MemberDefaultAvatar.class);
//
//        validateRequest(
//                memberDefaultAvatarEntity,
//                MemberDefaultAvatar.MEMBER_DEFAULT_AVATAR_ID,
//                MemberDefaultAvatar.APP_ID,
//                MemberDefaultAvatar.SYSTEM_VERSION
//        );
//
//        MemberDefaultAvatar result = memberDefaultAvatarService.delete(memberDefaultAvatarEntity.getMemberDefaultAvatarId(), memberDefaultAvatarEntity.getSystemRequestUserId(), memberDefaultAvatarEntity.getSystemVersion());
//
//        Boolean success = false;
//
//        if (result != null) {
//            MemberDefaultAvatarView memberDefaultAvatarView = JSON.parseObject(result.toJSONString(), MemberDefaultAvatarView.class);
//            memberDefaultAvatarService.update(memberDefaultAvatarView);
//        }
//
//        return renderJson(true);
//    }
//
//    @ApiOperation(value = "用户默认头像数据同步")
//    @RequestMapping(value = "/member/default/avatar/admin/v1/synchronize", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> synchronizeV1() {
//        List<MemberDefaultAvatar> memberDefaultAvatarList = memberDefaultAvatarService.listByMysql();
//
//        for (MemberDefaultAvatar memberDefaultAvatar : memberDefaultAvatarList) {
//            MemberDefaultAvatarView memberDefaultAvatarView = new MemberDefaultAvatarView();
//            memberDefaultAvatarView.putAll(memberDefaultAvatar);
//            
//            File file = fileRpc.findByMysqlV1(memberDefaultAvatar.getUserAvatarFileId());
//
//            memberDefaultAvatarView.setUserAvatarFilePath(file.getFilePath());
//            memberDefaultAvatarService.saveOrUpdate(memberDefaultAvatarView);
//        }
//
//        return renderJson(true);
//    }

}