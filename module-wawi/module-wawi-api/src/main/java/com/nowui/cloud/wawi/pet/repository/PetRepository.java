package com.nowui.cloud.wawi.pet.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.wawi.pet.view.PetView;
import org.springframework.stereotype.Component;

/**
 * 宠物视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface PetRepository extends BaseRepository<PetView> {

}
