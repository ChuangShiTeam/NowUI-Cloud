package com.nowui.cloud.file.file.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;

import com.nowui.cloud.file.file.repository.FileRepository;
import com.nowui.cloud.file.file.view.FileView;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.nowui.cloud.file.file.entity.File;
import com.nowui.cloud.file.file.entity.enums.FileType;
import com.nowui.cloud.file.file.mapper.FileMapper;
import com.nowui.cloud.file.file.service.FileService;
import com.nowui.cloud.constant.Config;
import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.exception.BusinessException;
import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.util.FileUtil;
import com.nowui.cloud.util.Util;

/**
 * 文件业务实现
 *
 * @author marcus
 *
 * 2018-01-01
 */
@Service
public class FileServiceImpl extends SuperServiceImpl<FileMapper, File, FileRepository, FileView> implements FileService {

    @Autowired
    private Config config;

    @Override
    public Integer countForAdmin(String appId, String systemCreateUserId, String fileName, String fileType) {
        Criteria criteria = Criteria.where(FileView.APP_ID).is(appId)
                .and(FileView.FILE_NAME).regex(".*?" + fileName + ".*")
                .and(FileView.FILE_TYPE).is(fileType)
                .and(FileView.SYSTEM_STATUS).is(true);

        Query query = new Query(criteria);

        Integer count = count(query);

        return count;
    }

    @Override
    public List<FileView> listForAdmin(String appId, String systemCreateUserId, String fileName, String fileType, Integer pageIndex, Integer pageSize) {
        Criteria criteria = Criteria.where(File.APP_ID).is(appId)
                .and(FileView.FILE_NAME).regex(".*?" + fileName + ".*")
                .and(FileView.FILE_TYPE).is(fileType)
                .and(FileView.SYSTEM_STATUS).is(true);

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(new Sort.Order(Sort.Direction.DESC, FileView.SYSTEM_CREATE_TIME));
        Sort sort = Sort.by(orders);

        Query query = new Query(criteria);
        query.with(sort);

        List<FileView> fileViewList = list(query, sort, pageIndex, pageSize);

        return fileViewList;
    }

    @Override
    public List<File> uploadImage(String appId, String userId, MultipartFile[] multipartFiles) {
        String path = Util.createPath(FileUtil.uploadPath, appId, userId);
        String thumbnailPath = Util.createPath(FileUtil.uploadPath, appId, userId, Constant.THUMBNAIL);
        String originalPath = Util.createPath(FileUtil.uploadPath, appId, userId, Constant.ORIGINAL);

        FileUtil.createPath(path);
        FileUtil.createPath(thumbnailPath);
        FileUtil.createPath(originalPath);

        List<File> fileList = new ArrayList<File>();
        for (MultipartFile myfile : multipartFiles) {
            if (myfile.isEmpty()) {
                throw new BusinessException("上传图片为空");
            } else {
                String fileId = Util.getRandomUUID();
                String originalFileName = myfile.getOriginalFilename();
                String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
                String fileName = fileId + "." + fileSuffix;

                if (!Constant.UPLOAD_IMAGE_TYPES.contains(fileSuffix)) {
                    throw new BusinessException("上传图片格式不对");
                }
                //图片限定10M
                if (myfile.getSize() > 10 * 1024L * 1024L) {
                    throw new BusinessException("上传图片超过限定大小");
                }

                java.io.File file = new java.io.File(originalPath, fileName);
                try {
                    FileUtil.copyInputStreamToFile(myfile.getInputStream(), file);
                } catch (IOException e) {
                    throw new BusinessException("上传图片失败");
                }

                path = path + fileName;
                thumbnailPath = thumbnailPath + fileName;
                originalPath = originalPath + fileName;

                String fileType = FileType.IMAGE.getKey();

                FileUtil.resizeImage(file, fileSuffix, thumbnailPath, 100);
                FileUtil.resizeImage(file, fileSuffix, path, 360);

                Integer fileSize = (int) file.length();
                String filePath = path.replace(config.getUploadFilePath(), "");
                String fileThumbnailPath = thumbnailPath.replace(config.getUploadFilePath(), "");
                String fileOriginalPath = originalPath.replace(config.getUploadFilePath(), "");
                Boolean fileIsOuter = false;

                File entity = new File();
                entity.setAppId(appId);
                entity.setFileName(originalFileName);
                entity.setFileIsOuter(fileIsOuter);
                entity.setFileOuterLink("");
                entity.setFileOriginalPath(fileOriginalPath);
                entity.setFilePath(filePath);
                entity.setFileSize(fileSize);
                entity.setFileSuffix(fileSuffix);
                entity.setFileThumbnailPath(fileThumbnailPath);
                entity.setFileType(fileType);
                entity.setFileCoverImage("");

                File result = save(entity, fileId, userId);

                if (result == null) {
                    throw new BusinessException("上传不成功");
                }

                fileList.add(entity);
            }
        }
        return fileList;
    }

    @Override
    public File uploadBase64(String appId, String userId, String base64Data) {
        String path = Util.createPath(FileUtil.uploadPath, appId, userId);
        String thumbnailPath = Util.createPath(FileUtil.uploadPath, appId, userId, Constant.THUMBNAIL);
        String originalPath = Util.createPath(FileUtil.uploadPath, appId, userId, Constant.ORIGINAL);

        FileUtil.createPath(path);
        FileUtil.createPath(thumbnailPath);
        FileUtil.createPath(originalPath);

        String fileSuffix = base64Data.substring(11, base64Data.indexOf(";base64,"));

        String fileId = Util.getRandomUUID();

        String fileName = fileId + "." + fileSuffix;

        String imageString = base64Data.substring(base64Data.indexOf(","));

        byte[] imageByte = Base64.decodeBase64(imageString.getBytes());

        path = path + fileName;
        thumbnailPath = thumbnailPath + fileName;
        originalPath = originalPath + fileName;

        java.io.File imageFile = new java.io.File(originalPath);

        try {
            FileImageOutputStream fos = new FileImageOutputStream(imageFile);
            fos.write(imageByte);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!Constant.UPLOAD_IMAGE_TYPES.contains(fileSuffix)) {
            throw new BusinessException("上传图片格式不对");
        }

        FileUtil.resizeImage(imageFile, fileSuffix, thumbnailPath, 100);
        FileUtil.resizeImage(imageFile, fileSuffix, path, 360);

        String fileType = FileType.IMAGE.getKey();
        Integer fileSize = (int) imageFile.length();
        String filePath = path.replace(config.getUploadFilePath(), "");
        String fileThumbnailPath = thumbnailPath.replace(config.getUploadFilePath(), "");
        String fileOriginalPath = originalPath.replace(config.getUploadFilePath(), "");
        Boolean fileIsOuter = false;

        File entity = new File();
        entity.setAppId(appId);
        entity.setFileName(fileName);
        entity.setFileIsOuter(fileIsOuter);
        entity.setFileOuterLink("");
        entity.setFileOriginalPath(fileOriginalPath);
        entity.setFilePath(filePath);
        entity.setFileSize(fileSize);
        entity.setFileSuffix(fileSuffix);
        entity.setFileThumbnailPath(fileThumbnailPath);
        entity.setFileType(fileType);
        entity.setFileCoverImage("");

        File result = save(entity, fileId, userId);

        if (result == null) {
            throw new BusinessException("上传不成功");
        }

        return entity;
    }

    @Override
    public List<File> uploadVideo(String appId, String userId, String fileCoverImage,
                                  CommonsMultipartFile[] commonsMultipartFiles) {
        return null;
    }

    @Override
    public File downloadWechatHeadImgToNative(String appId, String userId, String wechatHeadImgUrl) {
        String fileId = Util.getRandomUUID();
        String fileName = fileId + ".jpg";
        String filePath = Util.createPath(FileUtil.uploadPath, appId, userId);

        FileUtil.createPath(filePath);

        filePath = filePath + fileName;

        File file = new File();
        try {
            FileUtil.saveFileToDisk(wechatHeadImgUrl, filePath);

            file.setFileId(fileId);
            file.setAppId(appId);
            file.setFileIsOuter(true);
            file.setFileSize(0);
            file.setFileCoverImage("");
            file.setFileName(fileName);
            file.setFileOuterLink(wechatHeadImgUrl);
            file.setFileType(FileType.IMAGE.getKey());
            file.setFilePath(filePath);
            file.setFileOriginalPath(filePath);
            file.setFileThumbnailPath(filePath);
            file.setFileSuffix(".jpg");

            File result = save(file, fileId, userId);

            if (result == null) {
                throw new BusinessException("保存失败");
            }
        } catch (Exception e) {
            return null;
        }

        return file;
    }


}