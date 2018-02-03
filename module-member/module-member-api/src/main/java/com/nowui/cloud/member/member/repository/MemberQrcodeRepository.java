package com.nowui.cloud.member.member.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.member.member.view.MemberQrcodeView;
import org.springframework.stereotype.Component;

/**
 * 会员二维码编号	视图访问组件接口
 *
 * @author shawn
 *
 * 2018-02-03
 */
@Component
public interface MemberQrcodeRepository extends BaseRepository<MemberQrcodeView> {

}
