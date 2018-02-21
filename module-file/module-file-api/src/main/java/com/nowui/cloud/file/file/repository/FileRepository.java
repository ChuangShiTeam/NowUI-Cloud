package com.nowui.cloud.file.file.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.file.file.view.FileView;
import org.springframework.stereotype.Component;

/**
 * 文件视图访问组件接口
 *
 * @author marcus
 *
 * 2018-02-04
 */
@Component
public interface FileRepository extends BaseRepository<FileView> {

}
