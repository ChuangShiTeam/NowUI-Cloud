package com.nowui.cloud.wawi.pet.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.util.TreeUtil;
import com.nowui.cloud.util.Util;
import com.nowui.cloud.wawi.pet.entity.PetCategory;
import com.nowui.cloud.wawi.pet.mapper.PetCategoryMapper;
import com.nowui.cloud.wawi.pet.service.PetCategoryService;

/**
 * 宠物分类业务实现
 *
 * @author marcus
 *
 * 2018-01-24
 */
@Service
public class PetCategoryServiceImpl extends BaseServiceImpl<PetCategoryMapper, PetCategory> implements PetCategoryService {

    /**
     * 应用宠物一级分类缓存KEY
     */
    public static final String PET_CATEGORY_TOP_LIST_BY_APP_ID = "pet_category_top_list_by_app_id_";

    /**
     * 应用宠物父分类下子分类列表缓存KEY
     */
    public static final String PET_CATEGORY_CHILDREN_BY_PARENT_ID = "pet_category_children_by_parent_id_";

    /**
     * 应用宠物分类下树形列表缓存KEY
     */
    public static final String PET_CATEGORY_TREE_LIST_BY_APP_ID = "pet_category_tree_list_by_app_id_";

    @Override
    public Integer countForAdmin(String appId, String petCategoryName, String petCategoryCode) {
        Integer count = count(
                new BaseWrapper<PetCategory>()
                        .eq(PetCategory.APP_ID, appId)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_NAME, petCategoryName)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_CODE, petCategoryCode)
                        .eq(PetCategory.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<PetCategory> listForAdmin(String appId, String petCategoryName, String petCategoryCode, Integer pageIndex, Integer pageSize) {
        List<PetCategory> petCategoryList = list(
                new BaseWrapper<PetCategory>()
                        .eq(PetCategory.APP_ID, appId)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_NAME, petCategoryName)
                        .likeAllowEmpty(PetCategory.PET_CATEGORY_CODE, petCategoryCode)
                        .eq(PetCategory.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(PetCategory.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return petCategoryList;
    }

    @Override
    public List<PetCategory> adminTreeList(String appId, Integer pageIndex, Integer pageSize) {
        List<PetCategory> list = lrange(PET_CATEGORY_TREE_LIST_BY_APP_ID + appId, pageIndex, pageSize);

        if (Util.isNullOrEmpty(list)) {
            List<PetCategory> topList = topList(appId, pageIndex, pageSize);

            List<PetCategory> childrenList = childrenList(appId);

            for (PetCategory petCategory : topList) {

                petCategory.put(
                        Constant.CHILDREN,
                        TreeUtil.getTreeChildrenList(
                                petCategory.getPetCategoryId(),
                                PetCategory.PET_CATEGORY_PARENT_ID,
                                childrenList,
                                PetCategory.PET_CATEGORY_NAME,
                                PetCategory.PET_CATEGORY_IMAGE,
                                PetCategory.PET_CATEGORY_SORT,
                                PetCategory.PET_CATEGORY_CODE
                        )
                );

                petCategory.keep(
                        PetCategory.PET_CATEGORY_ID,
                        PetCategory.PET_CATEGORY_NAME,
                        PetCategory.PET_CATEGORY_IMAGE,
                        PetCategory.PET_CATEGORY_CODE,
                        PetCategory.PET_CATEGORY_SORT,
                        Constant.CHILDREN
                );
            }
            adminAllTreeList(appId);
            return topList;
        }

        return list;
    }

    @Override
    public List<PetCategory> adminAllTreeList(String appId) {
        List<PetCategory> list = lrange(PET_CATEGORY_TREE_LIST_BY_APP_ID + appId);

        if (Util.isNullOrEmpty(list)) {
            List<PetCategory> topList = topList(appId);

            List<PetCategory> childrenList = childrenList(appId);

            for (PetCategory petCategory : topList) {

                petCategory.put(
                        Constant.CHILDREN,
                        TreeUtil.getTreeChildrenList(
                                petCategory.getPetCategoryId(),
                                PetCategory.PET_CATEGORY_PARENT_ID,
                                childrenList,
                                PetCategory.PET_CATEGORY_NAME,
                                PetCategory.PET_CATEGORY_IMAGE,
                                PetCategory.PET_CATEGORY_SORT,
                                PetCategory.PET_CATEGORY_CODE
                        )
                );

                petCategory.keep(
                        PetCategory.PET_CATEGORY_ID,
                        PetCategory.PET_CATEGORY_NAME,
                        PetCategory.PET_CATEGORY_IMAGE,
                        PetCategory.PET_CATEGORY_CODE,
                        PetCategory.PET_CATEGORY_SORT,
                        Constant.CHILDREN
                );
            }

            redis.opsForList().leftPushAll(PET_CATEGORY_TREE_LIST_BY_APP_ID + appId, topList.toArray());

            return topList;
        }

        return list;
    }

    @Override
    public List<PetCategory> topList(String appId) {
        List<PetCategory> topList = lrange(PET_CATEGORY_TOP_LIST_BY_APP_ID + appId);

        if (Util.isNullOrEmpty(topList)) {
            topList = list(
                    new BaseWrapper<PetCategory>()
                            .eq(PetCategory.APP_ID, appId)
                            .eq(PetCategory.PET_CATEGORY_PARENT_ID, "")
                            .eq(PetCategory.SYSTEM_STATUS, true)
                            .orderAsc(Arrays.asList(PetCategory.PET_CATEGORY_SORT))
            );

            redis.opsForList().leftPushAll(PET_CATEGORY_TOP_LIST_BY_APP_ID + appId, topList.toArray());
        }
        return topList;
    }

    @Override
    public List<PetCategory> topList(String appId, Integer pageIndex, Integer pageSize) {
        List<PetCategory> topList = lrange(PET_CATEGORY_TOP_LIST_BY_APP_ID + appId, pageIndex, pageSize);

        if (Util.isNullOrEmpty(topList)) {
            topList = list(
                    new BaseWrapper<PetCategory>()
                            .eq(PetCategory.APP_ID, appId)
                            .eq(PetCategory.PET_CATEGORY_PARENT_ID, "")
                            .eq(PetCategory.SYSTEM_STATUS, true)
                            .orderAsc(Arrays.asList(PetCategory.PET_CATEGORY_SORT)),
                    pageIndex,
                    pageSize
            );

            topList(appId);
        }

        return topList;
    }

    @Override
    public Integer countTop(String appId) {
        Integer count = lsize(PET_CATEGORY_TOP_LIST_BY_APP_ID + appId);

        if (count == 0) {
            count = count(
                    new BaseWrapper<PetCategory>()
                            .eq(PetCategory.APP_ID, appId)
                            .eq(PetCategory.PET_CATEGORY_PARENT_ID, "")
                            .eq(PetCategory.SYSTEM_STATUS, true)
            );

            topList(appId);
        }
        return count;
    }

    @Override
    public List<PetCategory> listByParentId(String parentId) {
        List<PetCategory> petCategoryList = lrange(PET_CATEGORY_CHILDREN_BY_PARENT_ID + parentId);

        if (Util.isNullOrEmpty(petCategoryList)) {
            petCategoryList = list(
                    new BaseWrapper<PetCategory>()
                            .eq(PetCategory.PET_CATEGORY_PARENT_ID, parentId)
                            .eq(PetCategory.SYSTEM_STATUS, true)
                            .orderAsc(Arrays.asList(PetCategory.PET_CATEGORY_SORT))
            );

            redis.opsForList().leftPushAll(PET_CATEGORY_CHILDREN_BY_PARENT_ID + parentId, petCategoryList.toArray());
        }
        return petCategoryList;
    }

    @Override
    public List<PetCategory> childrenList(String appId) {
        List<PetCategory> childrenList = list(
                new BaseWrapper<PetCategory>()
                        .eq(PetCategory.APP_ID, appId)
                        .ne(PetCategory.PET_CATEGORY_PARENT_ID, "")
                        .eq(PetCategory.SYSTEM_STATUS, true)
                        .orderAsc(Arrays.asList(PetCategory.PET_CATEGORY_SORT))
        );
        return childrenList;
    }

    @Override
    public Boolean save(PetCategory entity, String petCategoryId, String systemReuqestUserId) {
        Boolean result = super.save(entity, petCategoryId, systemReuqestUserId);
        if (result) {
            // 清空相关缓存
            redis.delete(PET_CATEGORY_TOP_LIST_BY_APP_ID + entity.getAppId());
            redis.delete(PET_CATEGORY_TREE_LIST_BY_APP_ID + entity.getAppId());
            redis.delete(PET_CATEGORY_CHILDREN_BY_PARENT_ID + entity.getPetCategoryParentId());
        }
        return result;
    }

    @Override
    public Boolean update(PetCategory entity, String petCategoryId, String systemReuqestUserId, Integer systemVersion) {
        Boolean result = super.update(entity, petCategoryId, systemReuqestUserId, systemVersion);

        if (result) {
            // 清空相关缓存
            redis.delete(PET_CATEGORY_TOP_LIST_BY_APP_ID + entity.getAppId());
            redis.delete(PET_CATEGORY_TREE_LIST_BY_APP_ID + entity.getAppId());
            redis.delete(PET_CATEGORY_CHILDREN_BY_PARENT_ID + entity.getPetCategoryParentId());
        }

        return result;
    }

    @Override
    public Boolean delete(String appId, String petCategoryId, String systemReuqestUserId, Integer systemVersion) {
        PetCategory petCategory = find(petCategoryId);

        Boolean result = super.delete(petCategoryId, systemReuqestUserId, systemVersion);

        if (result) {

            //删除子级分类
            List<PetCategory> list = listByParentId(petCategoryId);
            if (!Util.isNullOrEmpty(list)) {
                for (PetCategory entity : list) {
                    super.delete(entity.getPetCategoryId(), systemReuqestUserId, entity.getSystemVersion());
                }
            }
            // 清空相关缓存
            redis.delete(PET_CATEGORY_TOP_LIST_BY_APP_ID + appId);
            redis.delete(PET_CATEGORY_TREE_LIST_BY_APP_ID + appId);
            redis.delete(PET_CATEGORY_CHILDREN_BY_PARENT_ID + petCategoryId);
            // 删除父级缓存
            if (!Util.isNullOrEmpty(petCategory.getPetCategoryParentId())) {
                redis.delete(PET_CATEGORY_CHILDREN_BY_PARENT_ID + petCategory.getPetCategoryParentId());
            }
        }

        return result;
    }

}