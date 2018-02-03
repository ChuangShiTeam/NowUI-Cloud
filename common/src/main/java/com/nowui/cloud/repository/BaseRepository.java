package com.nowui.cloud.repository;

import com.nowui.cloud.view.BaseView;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 数据访问组件接口
 *
 * @author ZhongYongQiang
 *
 * 2018-01-29
 */
public interface BaseRepository<V extends BaseView> extends MongoRepository<V, String> {


}
