package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    // 查询所有资源分类（条件查询）
    @Override
    public PageInfo<Resource> findAllResource(ResourceVo resourceVo) {

        // 分页查询
        PageHelper.startPage(resourceVo.getCurrentPage(), resourceVo.getPageSize());
        List<Resource> list = resourceMapper.findAllResource(resourceVo);

        PageInfo<Resource> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /*
        根据资源分类id查询资源信息
     */
    @Override
    public List<Resource> findResourceByCategoryId(Integer categoryId) {
        return resourceMapper.findResourceByCategoryId(categoryId);
    }

    // 添加资源
    @Override
    public void saveResource(Resource resource) {
        // 补全信息
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);

        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");

        resourceMapper.saveResource(resource);
    }

    // 回显资源
    @Override
    public Resource findResourceById(Integer id) {
        return resourceMapper.findResourceById(id);
    }

    // 修改资源
    @Override
    public void updateResource(Resource resource) {
        resource.setUpdatedTime(new Date());
        resource.setUpdatedBy("管理员");
        resourceMapper.updateResource(resource);
    }

    // 删除资源信息
    @Override
    public void deleteResource(Integer id) {
        resourceMapper.deleteResource(id);
    }

    // 根据角色id查询角色信息
    @Override
    public List<Resource> findResourceByRoleId(Integer roleId) {
        return resourceMapper.findResourceByRoleId(roleId);
    }
}
