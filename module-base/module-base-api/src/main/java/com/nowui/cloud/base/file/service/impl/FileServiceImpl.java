package com.nowui.cloud.base.file.service.impl;

import com.nowui.cloud.mybatisplus.BaseWrapper;
import com.nowui.cloud.service.impl.BaseServiceImpl;
import com.nowui.cloud.base.file.entity.File;
import com.nowui.cloud.base.file.mapper.FileMapper;
import com.nowui.cloud.base.file.service.FileService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 文件业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class FileServiceImpl extends BaseServiceImpl<FileMapper, File> implements FileService {

    @Override
    public Integer adminCount(String appId, String fileName) {
        Integer count = count(
                new BaseWrapper<File>()
                        .eq(File.APP_ID, appId)
                        .likeAllowEmpty(File.FILE_NAME, fileName)
                        .eq(File.SYSTEM_STATUS, true)
        );
        return count;
    }

    @Override
    public List<File> adminList(String appId, String fileName, Integer m, Integer n) {
        List<File> fileList = list(
                new BaseWrapper<File>()
                        .eq(File.APP_ID, appId)
                        .likeAllowEmpty(File.FILE_NAME, fileName)
                        .eq(File.SYSTEM_STATUS, true)
                        .orderDesc(Arrays.asList(File.SYSTEM_CREATE_TIME)),
                m,
                n
        );

        return fileList;
    }

}