package com.nowui.cloud.base.file.service;
import com.nowui.cloud.service.BaseService;
import com.nowui.cloud.base.file.entity.File;

import java.util.List;

/**
 * 文件业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface FileService extends BaseService<File> {

    /**
     * 文件统计
     *
     * @param appId 应用编号
     * @param fileName 名称
     * @return Integer 文件统计
     */
    Integer adminCount(String appId, String fileName);

    /**
     * 文件列表
     *
     * @param appId 应用编号
     * @param fileName 名称
     * @param m 从m条开始
     * @param n 取n条数据
     * @return List<File> 文件列表
     */
    List<File> adminList(String appId, String fileName, Integer m, Integer n);
}
