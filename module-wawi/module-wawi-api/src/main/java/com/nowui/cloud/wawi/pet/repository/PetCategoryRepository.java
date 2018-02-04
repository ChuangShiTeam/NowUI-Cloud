package com.nowui.cloud.wawi.pet.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.wawi.pet.view.PetCategoryView;
import org.springframework.stereotype.Component;

/**
 * 宠物分类视图访问组件接口
 *
 * @author xupengfei
 *
 * 2018-02-04
 */
@Component
public interface PetCategoryRepository extends BaseRepository<PetCategoryView> {

}
