package com.nowui.cloud.wawi.pet.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.wawi.pet.entity.Pet;

import java.util.List;

/**
 * 宠物业务接口
 *
 * @author hucy
 *
 * 2018-01-21
 */
public interface PetService extends BaseService<Pet> {

    /**
     * 宠物统计
     *
     * @param appId 应用编号
     * @param petCategoryId 宠物分类编号
     * @param petName 宠物名称
     * @return Integer 宠物统计
     */
    Integer countForAdmin(String appId, String petCategoryId, String petName);

    /**
     * 宠物列表
     *
     * @param appId 应用编号
     * @param petCategoryId 宠物分类编号
     * @param petName 宠物名称
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<Pet> 宠物列表
     */
    List<Pet> listForAdmin(String appId, String petCategoryId, String petName, Integer pageIndex, Integer pageSize);
}
