package com.nowui.cloud.wawi.pet.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.wawi.pet.entity.PetCategory;

import java.util.List;

/**
 * 宠物分类业务接口
 *
 * @author marcus
 *
 * 2018-01-16
 */
public interface PetCategoryService extends BaseService<PetCategory> {

    /**
     * 宠物分类统计
     *
     * @param appId 应用编号
     * @param petCategoryName 宠物分类名称
     * @param petCategoryCode 宠物分类编码
     * @param petCategoryImage 宠物分类图片
     * @return Integer 宠物分类统计
     */
    Integer countForAdmin(String appId, String petCategoryName, String petCategoryCode, String petCategoryImage);

    /**
     * 宠物分类列表
     *
     * @param appId 应用编号
     * @param petCategoryName 宠物分类名称
     * @param petCategoryCode 宠物分类编码
     * @param petCategoryImage 宠物分类图片
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<PetCategory> 宠物分类列表
     */
    List<PetCategory> listForAdmin(String appId, String petCategoryName, String petCategoryCode, String petCategoryImage, Integer pageIndex, Integer pageSize);
}
