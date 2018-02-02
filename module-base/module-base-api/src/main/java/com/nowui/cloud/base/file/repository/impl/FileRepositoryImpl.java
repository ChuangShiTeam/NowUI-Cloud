package com.nowui.cloud.base.file.repository.impl;

import org.springframework.stereotype.Component;

import com.nowui.cloud.base.file.repository.FileRepository;
import com.nowui.cloud.base.file.view.FileView;
import com.nowui.cloud.repository.impl.BaseRepositoryImpl;

/**
 * 文件视图访问组件接口实现
 *
 * @author marcus
 *
 * 2018-02-02
 */
@Component
public class FileRepositoryImpl extends BaseRepositoryImpl<FileView> implements FileRepository {

}
