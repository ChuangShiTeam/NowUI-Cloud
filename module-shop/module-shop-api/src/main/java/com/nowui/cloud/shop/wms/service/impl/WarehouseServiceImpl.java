package com.nowui.cloud.shop.wms.service.impl;

import com.nowui.cloud.service.impl.SuperServiceImpl;
import com.nowui.cloud.shop.wms.entity.Warehouse;
import com.nowui.cloud.shop.wms.mapper.WarehouseMapper;
import com.nowui.cloud.shop.wms.repository.WarehouseRepository;
import com.nowui.cloud.shop.wms.service.WarehouseService;
import com.nowui.cloud.shop.wms.view.WarehouseView;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 仓库信息管理业务实现
 *
 * @author lijingqiang
 *
 * 2018-03-05
 */
@Service
public class WarehouseServiceImpl extends SuperServiceImpl<WarehouseMapper, Warehouse, WarehouseRepository, WarehouseView> implements WarehouseService {

    @Override
        public Integer countForAdmin(String appId, String warehouseName) {
            Criteria criteria = Criteria.where(WarehouseView.APP_ID).is(appId)
                    .and(WarehouseView.WAREHOUSE_NAME).regex(".*?" + warehouseName + ".*")
                    .and(WarehouseView.SYSTEM_STATUS).is(true);

            Query query = new Query(criteria);

            Integer count = count(query);

            return count;
        }

        @Override
        public List<WarehouseView> listForAdmin(String appId, String warehouseName, Integer pageIndex, Integer pageSize) {
            Criteria criteria = Criteria.where(Warehouse.APP_ID).is(appId)
                    .and(WarehouseView.WAREHOUSE_NAME).regex(".*?" + warehouseName + ".*")
                    .and(WarehouseView.SYSTEM_STATUS).is(true);

            List<Order> orders = new ArrayList<Order>();
            orders.add(new Order(Sort.Direction.DESC, WarehouseView.SYSTEM_CREATE_TIME));
            Sort sort = Sort.by(orders);

            Query query = new Query(criteria);
            query.with(sort);

            List<WarehouseView> warehouseViewList = list(query, sort, pageIndex, pageSize);

            return warehouseViewList;
        }

}