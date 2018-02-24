package com.nowui.cloud.file.file.service;
import com.nowui.cloud.file.file.view.FileView;
import com.nowui.cloud.service.SuperService;
import com.nowui.cloud.file.file.entity.File;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 文件业务接口
 *
 * @author marcus
 *
 * 2018-01-01
 */
public interface FileService extends SuperService<File,FileView> {

    /**
     * 文件统计
     *
     * @param appId 应用编号
     * @param systemCreateUserId 创建用户编号
     * @param fileName 名称
     * @param fileType 类型
     * @return Integer 文件统计
     */
    Integer countForAdmin(String appId, String systemCreateUserId, String fileName, String fileType);

    /**
     * 文件列表
     *
     * @param appId 应用编号
     * @param systemCreateUserId 创建用户编号
     * @param fileName 名称
     * @param fileType 类型
     * @param pageIndex 页码
     * @param pageSize 每页个数
     * @return List<File> 文件列表
     */
    List<FileView> listForAdmin(String appId, String systemCreateUserId, String fileName, String fileType, Integer pageIndex, Integer pageSize);
    
    /**
     * 视频上传
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param fileCoverImage 视频封面
     * @param commonsMultipartFiles  视频读取
     * @return List<File> 视频列表
     */
    List<File> uploadVideo(String appId, String userId, String fileCoverImage, CommonsMultipartFile[] commonsMultipartFiles);
    
    /**
     * 图片上传
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param multipartFiles  图片读取
     * @return List<File> 图片列表
     */
    List<File> uploadImage(String appId, String userId, MultipartFile[] multipartFiles);
    
    /**
     * base64图片上传
     * @param appId 应用编号
     * @param userId 用户编号
     * @param base64Data base64编码数据
     * @return File 文件信息
     */
    File uploadBase64(String appId, String userId, String base64Data);
    
    /**
     * 下载微信头像到本地并保存
     * 
     * @param appId 应用编号
     * @param userId 用户编号
     * @param wechatHeadImgUrl 微信头像地址
     * @return File 文件信息
     */
    File downloadWechatHeadImgToNative(String appId, String userId, String wechatHeadImgUrl);
}
