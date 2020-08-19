package com.java.until;

import com.java.moudle.system.domain.SysFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZhangWei
 * @Description
 * @Date: 2020-03-20 09:23
 **/
public class TreeUtil {

    /**
     * @Description 权限树
     * @Author zw
     * @Date 09:31 2020-03-20
     * @Param
     * @return java.util.List<com.java.moudle.system.domain.SysFunction>
     **/
    public static List<SysFunction> getTreeList(List<SysFunction> entityList) {
        List<SysFunction> resultList = new ArrayList<>();

        //获取顶层元素集合
        String parentCode;
        for (SysFunction entity : entityList) {
            parentCode = entity.getParentId();
            //顶层元素的parentCode==null或者为0
            if (parentCode == null || "0".equals(parentCode)) {
                resultList.add(entity);
            }
        }

        //获取每个顶层元素的子数据集合
        for (SysFunction entity : resultList) {
            entity.setSysFunctionList(getSubList(entity.getId(), entityList));
        }

        return resultList;
    }

    /**
     * 获取子数据集合
     *
     * @param id
     * @param entityList
     * @return
     * @author zw
     * @date
     */
    private static List<SysFunction> getSubList(String id, List<SysFunction> entityList) {
        List<SysFunction> childList = new ArrayList<>();
        String parentId;

        //子集的直接子对象
        for (SysFunction entity : entityList) {
            parentId = entity.getParentId();
            if (id.equals(parentId)) {
                childList.add(entity);
            }
        }

        //子集的间接子对象
        for (SysFunction entity : childList) {
            entity.setSysFunctionList(getSubList(entity.getId(), entityList));
        }

        //递归退出条件
        if (childList.size() == 0) {
            return null;
        }

        return childList;
    }


}
