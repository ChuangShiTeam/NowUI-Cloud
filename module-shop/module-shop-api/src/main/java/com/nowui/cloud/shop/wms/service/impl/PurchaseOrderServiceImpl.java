package com.nowui.cloud.shop.wms.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.shop.wms.entity.PurchaseOrder;
import com.nowui.cloud.shop.wms.mapper.PurchaseOrderMapper;
import com.nowui.cloud.shop.wms.repository.PurchaseOrderRepository;
import com.nowui.cloud.shop.wms.service.PurchaseOrderService;
import com.nowui.cloud.shop.wms.view.PurchaseOrderView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 采购申请单业务实现
 *
 * @author lyn
 *
 * 2018-03-05
 */
@Service
public class PurchaseOrderServiceImpl extends SuperServiceImpl<PurchaseOrderMapper, PurchaseOrder, PurchaseOrderRepository, PurchaseOrderView> implements PurchaseOrderService {

    @Override
        public Integer countForAdmin(String appId, String purchaseNo, String purchaseName, String purchaseType, String purchaseUserName) {
            Criteria criteria = Criteria.where(PurchaseOrderView.APP_ID).is(appId)
                    .and(PurchaseOrderView.PURCHASE_NO).regex(".*?" + purchaseNo + ".*")
                    .and(PurchaseOrderView.PURCHASE_NAME).regex(".*?" + purchaseName + ".*")
                    .and(PurchaseOrderView.PURCHASE_TYPE).regex(".*?" + purchaseType + ".*")
                    .and(PurchaseOrderView.PURCHASE_USER_NAME).regex(".*?" + purchaseUserName + ".*")
                    .and(PurchaseOrderView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<PurchaseOrderView> listForAdmin(String appId, String purchaseNo, String purchaseName, String purchaseType, String purchaseUserName, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(PurchaseOrder.APP_ID).is(appId)
                    .and(PurchaseOrderView.PURCHASE_NO).regex(".*?" + purchaseNo + ".*")
                    .and(PurchaseOrderView.PURCHASE_NAME).regex(".*?" + purchaseName + ".*")
                    .and(PurchaseOrderView.PURCHASE_TYPE).regex(".*?" + purchaseType + ".*")
                    .and(PurchaseOrderView.PURCHASE_USER_NAME).regex(".*?" + purchaseUserName + ".*")
                    .and(PurchaseOrderView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, PurchaseOrderView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<PurchaseOrderView> purchaseOrderViewList = list(query, sort, pageIndex, pageSize);

            return purchaseOrderViewList;
        }

}