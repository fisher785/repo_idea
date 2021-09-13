package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;

import java.util.List;

public interface ResourceService {


    /*
        查询所有资源分类（条件查询）
     */
    PageInfo<Resource> findAllResource(ResourceVo resourceVo);

    /*
        根据分类id查询查询资源
     */
    List<Resource> findResourceByCategoryId(Integer categoryId);

    /*
        添加资源
     */
    void saveResource(Resource resource);

    /**
     * 根据id查询资源
     */
    Resource findResourceById(Integer id);

    /*
        修改资源
     */

    void updateResource(Resource resource);
    /*
        根据角色id查找资源
     */

    List<Resource> findResourceByRoleId(Integer roleId);

    /**
     * 删除资源信息
     */
    void deleteResource(Integer id);
}
