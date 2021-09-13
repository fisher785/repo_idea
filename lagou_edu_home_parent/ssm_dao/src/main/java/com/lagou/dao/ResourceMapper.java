package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceMapper {

    /*
        查询所有资源分类（条件查询）
     */

    List<Resource> findAllResource(ResourceVo resourceVo);

    /*
        根据分类id查询查询资源
     */
    List<Resource> findResourceByCategoryId(Integer categoryId);

    /*
        添加资源
     */
    void saveResource(Resource resource);

    /**
     * 根据id查询资源（回显）
     */
    Resource findResourceById(Integer id);

    /*
        修改资源
     */
    void updateResource(Resource resource);

    /**
     * 删除资源信息
     */
    void deleteResource(Integer id);

    /*
        根据角色id查找资源
     */
    List<Resource> findResourceByRoleId(Integer roleId);
}
