package com.nowui.cloud.wawi.pet.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.mapper.PetCategoryMapper;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 宠物分类业务实现
 *
 * @author marcus
 *
 * 2018-01-16
 */
@Service
public class PetCategoryServiceImpl extends BaseServiceImpl<PetCategoryMapper, PetCategory> implements PetCategoryService {

    @Override
    public Integer countForAdmin(String appId, String petCategoryName, String petCategoryCode, String petCategoryImage) {
        Integer count = count(
                new BaseWrapper<PetCategory>()
                        .eq(PetCategory.APP_ID, appId)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_NAME, petCategoryName)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_CODE, petCategoryCode)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_IMAGE, petCategoryImage)
                        .eq(PetCategory.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<PetCategory> listForAdmin(String appId, String petCategoryName, String petCategoryCode, String petCategoryImage, Integer pageIndex, Integer pageSize) {
        List<PetCategory> petCategoryList = list(
                new BaseWrapper<PetCategory>()
                        .eq(PetCategory.APP_ID, appId)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_NAME, petCategoryName)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_CODE, petCategoryCode)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_IMAGE, petCategoryImage)
                        .eq(PetCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(PetCategory.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return petCategoryList;
    }

}