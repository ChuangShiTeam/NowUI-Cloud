package com.nowui.cloud.wawi.pet.service;
import java.util.List;

import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.wawi.pet.entity.PetCategory;

/**
 * 宠物分类业务接口
 *
 * @author marcus
 *
 * 2018-01-24
 */
public interface PetCategoryService extends BaseService<PetCategory> {

    /**
     * 宠物分类统计
     *
     * @param appId 应用编号
     * @param petCategoryName 宠物分类名称
     * @param petCategoryCode 宠物分类编码
     * @return Integer 宠物分类统计
     */
    Integer countForAdmin(String appId, String petCategoryName, String petCategoryCode);

    /**
     * 宠物分类列表
     *
     * @param appId 应用编号
     * @param petCategoryName 宠物分类名称
     * @param petCategoryCode 宠物分类编码
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<PetCategory> 宠物分类列表
     */
    List<PetCategory> listForAdmin(String appId, String petCategoryName, String petCategoryCode, Integer pageIndex, Integer pageSize);

    /**
     * 宠物分类树形分页列表
     *
     * @param appId 应用编号
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<PetCategory> 宠物分类树形分页列表
     */
    List<PetCategory> adminTreeList(String appId, Integer pageIndex, Integer pageSize);

    /**
     * 所有宠物分类树形列表
     *
     * @param appId 应用编号
     * @return List<PetCategory> 所有宠物分类树形列表
     */
    List<PetCategory> adminAllTreeList(String appId);

    /**
     * 查询所有宠物一级分类列表
     *
     * @param appId 应用编号
     * @return List<PetCategory> 宠物一级分类列表
     */
    List<PetCategory> topList(String appId);

    /**
     * 查询宠物一级分类分页列表
     *
     * @param appId 应用编号
     * @return List<PetCategory> 宠物一级分类列表
     */
    List<PetCategory> topList(String appId, Integer pageIndex, Integer pageSize);

    /**
     * 统计宠物一级分类个数
     * @param appId 应用编号
     * @return Integer 宠物一级分类个数
     */
    Integer countTop(String appId);

    /**
     * 根据父分类编号查询宠物分类下的子分类列表
     *
     * @param parentId 父级编号
     * @return List<PetCategory> 宠物分类列表
     */
    List<PetCategory> listByParentId(String parentId);

    /**
     * 查询宠物分类下的所有子分类列表
     *
     * @param appId 应用编号
     * @return List<PetCategory> 宠物分类列表
     */
    List<PetCategory> childrenList(String appId);

    /**
     * 删除宠物分类
     *
     * @param appId 应用编号
     * @param petCategoryId 宠物分类编号
     * @param systemReuqestUserId 请求用户编号
     * @param systemVersion 版本号
     * @return Boolean 是否成功
     */
    Boolean delete(String appId, String petCategoryId, String systemReuqestUserId, Integer systemVersion);
}
