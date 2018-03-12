package com.nowui.cloud.wawi.pet.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.wawi.pet.entity.Pet;
import com.nowui.cloud.wawi.pet.mapper.PetMapper;
import com.nowui.cloud.wawi.pet.repository.PetRepository;
import com.nowui.cloud.wawi.pet.service.PetService;
import com.nowui.cloud.wawi.pet.view.PetView;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 宠物业务实现
 *
 * @author hucy
 *
 * 2018-01-21
 */
@Service
public class PetServiceImpl extends BaseServiceImpl<PetMapper, Pet, PetRepository, PetView> implements PetService {

    @Override
    public Integer countForAdmin(String appId, String petCategoryId, String petName) {
        Integer count = count(
                new BaseWrapper<Pet>()
                        .eq(Pet.APP_ID, appId)
                        .eq(Pet.PET_CATEGORY_ID, petCategoryId)
                        .likeAllowEmpty(Pet.PET_NAME, petName)
                        .eq(Pet.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<Pet> listForAdmin(String appId, String petCategoryId, String petName, Integer pageIndex, Integer pageSize) {
        List<Pet> petList = list(
                new BaseWrapper<Pet>()
                        .eq(Pet.APP_ID, appId)
                        .eq(Pet.PET_CATEGORY_ID, petCategoryId)
                        .likeAllowEmpty(Pet.PET_NAME, petName)
                        .eq(Pet.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Pet.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return petList;
    }

    @Override
    public List<Pet> listByUserId(String appId, String userId, Integer pageIndex, Integer pageSize) {
        List<Pet> petList = list(
                new BaseWrapper<Pet>()
                        .eq(Pet.APP_ID, appId)
                        .eq(Pet.USER_ID, userId)
                        .eq(Pet.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(Pet.SYSTEM_CREATE_TIME)),
                pageIndex,
                pageSize
        );

        return petList;
    }

    @Override
    public Integer countByUserId(String appId, String userId) {
        Integer count = count(
                new BaseWrapper<Pet>()
                        .eq(Pet.APP_ID, appId)
                        .eq(Pet.USER_ID, userId)
                        .eq(Pet.SYSTEM_STATUS, true)
        );
        return count;
    }
}