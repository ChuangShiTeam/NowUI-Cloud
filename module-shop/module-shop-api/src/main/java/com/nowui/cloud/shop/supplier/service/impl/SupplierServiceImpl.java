package com.nowui.cloud.shop.supplier.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.shop.supplier.entity.Supplier;
import com.nowui.cloud.shop.supplier.mapper.SupplierMapper;
import com.nowui.cloud.shop.supplier.repository.SupplierRepository;
import com.nowui.cloud.shop.supplier.service.SupplierService;
import com.nowui.cloud.shop.supplier.view.SupplierView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商基本信息业务实现
 *
 * @author lyn
 *
 * 2018-03-06
 */
@Service
public class SupplierServiceImpl extends SuperServiceImpl<SupplierMapper, Supplier, SupplierRepository, SupplierView> implements SupplierService {

    @Override
        public Integer countForAdmin(String appId, String supplierName) {
            Criteria criteria = Criteria.where(SupplierView.APP_ID).is(appId)
                    .and(SupplierView.SUPPLIER_NAME).regex(".*?" + supplierName + ".*")
                    .and(SupplierView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<SupplierView> listForAdmin(String appId, String supplierName, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(Supplier.APP_ID).is(appId)
                    .and(SupplierView.SUPPLIER_NAME).regex(".*?" + supplierName + ".*")
                    .and(SupplierView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, SupplierView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<SupplierView> supplierViewList = list(query, sort, pageIndex, pageSize);

            return supplierViewList;
        }

}