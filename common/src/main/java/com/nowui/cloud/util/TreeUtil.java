package com.nowui.cloud.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nowui.cloud.constant.Constant;
import com.nowui.cloud.entity.BaseEntity;

public class TreeUtil {
    
    /**
     * 递归遍历生成树形结构数据
     * 
     * @param parentId 父级编号
     * @param parentColumnName 父级列名
     * @param entityList 实体列表
     * @param columns 返回列表的列数据
     * @return List<Map<String, Object>> 树形列表
     */
//    public static List<? extends BaseEntity> getTreeChildrenList(String parentId, String parentColumnName, List<? extends BaseEntity> entityList, String ...columns) {
//        List<BaseEntity> list = new ArrayList<>();
//        if (entityList != null && entityList.size() > 0) {
//            for (BaseEntity entity : entityList) {
//                if (parentId.equals(entity.get(parentColumnName))) {
//                    String idColumn = entity.getTableId();
//                    String id = entity.getString(idColumn);
//
//                    List<? extends BaseEntity> childrenList = getTreeChildrenList(id, parentColumnName, entityList, columns);
//
//                    entity.keep(columns);
//
//                    entity.put(idColumn, id);
//
//                    entity.put(Constant.CHILDREN, childrenList);
//
//                    list.add(entity);
//                }
//            }
//        }
//
//        return list;
//    }

}
