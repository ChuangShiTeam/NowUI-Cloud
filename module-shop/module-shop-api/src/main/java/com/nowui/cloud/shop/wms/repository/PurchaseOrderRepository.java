package com.nowui.cloud.shop.wms.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.shop.wms.view.PurchaseOrderView;
import org.springframework.stereotype.Component;

/**
 * 采购申请单视图访问组件接口
 *
 * @author lyn
 *
 * 2018-03-05
 */
@Component
public interface PurchaseOrderRepository extends BaseRepository<PurchaseOrderView> {

}
