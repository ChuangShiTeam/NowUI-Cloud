package com.nowui.cloud.shop.supplier.repository;

import com.nowui.cloud.repository.BaseRepository;
import com.nowui.cloud.shop.supplier.view.SupplierView;
import org.springframework.stereotype.Component;

/**
 * 供应商基本信息视图访问组件接口
 *
 * @author lyn
 *
 * 2018-03-06
 */
@Component
public interface SupplierRepository extends BaseRepository<SupplierView> {

}
