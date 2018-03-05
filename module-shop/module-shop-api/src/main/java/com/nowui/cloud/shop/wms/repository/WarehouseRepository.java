package com.nowui.cloud.shop.wms.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.shop.wms.view.WarehouseView;
import org.springframework.stereotype.Component;

/**
 * 仓库信息管理视图访问组件接口
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
@Component
public interface WarehouseRepository extends BaseRepository<WarehouseView> {

}
