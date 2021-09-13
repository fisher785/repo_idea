package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    /*
        查询所有资源分类
     */
    List<ResourceCategory> findAllResourceCategory();

    /*
        添加资源分类
     */
    void saveResourceCategory(ResourceCategory resourceCategory);

    /*
        资源分类信息（回显）
     */
    ResourceCategory findResourceCategoryById(Integer id);

    /*
        修改资源分类
     */
    void updateResourceCategory(ResourceCategory resourceCategory);

    /*
        删除资源分类
     */
    void deleteResourceCategoryById(Integer id);

    /*
        查询角色拥有的资源分类信息
     */
    List<ResourceCategory> findResourceCategoryByRoleId(Integer roleId);
}
