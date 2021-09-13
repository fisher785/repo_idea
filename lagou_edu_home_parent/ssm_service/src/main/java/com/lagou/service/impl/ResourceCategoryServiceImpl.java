package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /*
        查询所有分类资源
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    /*
        添加资源分类
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        // 补全信息
        Date date = new Date();
        // 补全创建事件和修改事件，要注意格式问题，所以在实体类中添加了@DateTimeFormat(pattern = "yyyy-MM-dd")
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        // 创建者和修改者，可以通过 查找session中的userId对应的用户名 来获取，这里先手动录入
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        // 调用dao层中的方法
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    /*
        根据id查找资源分类（回显）
     */
    @Override
    public ResourceCategory findResourceCategoryById(Integer id) {
        return resourceCategoryMapper.findResourceCategoryById(id);
    }

    /*
        修改资源分类信息
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        // 补全信息
        resourceCategory.setUpdatedTime(new Date());
        // 修改者，可以通过 查找session中的userId对应的用户名 来获取，这里先手动录入
        resourceCategory.setUpdatedBy("update");
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /*
        根据id删除资源分类
     */
    @Override
    public void deleteResourceCategoryById(Integer id) {
        resourceCategoryMapper.deleteResourceCategoryById(id);
    }


    @Autowired
    private ResourceService resourceService;
    /*
        根绝角色id查找资源分类（含资源信息）
     */
    @Override
    public List<ResourceCategory> findResourceCategoryWithResourceByRoleId(Integer roleId) {
        // 查找到分类信息
        List<ResourceCategory> categoryList = resourceCategoryMapper.findResourceCategoryByRoleId(roleId);

        // 查找到所有资源信息
        List<Resource> resourceList = resourceService.findResourceByRoleId(roleId);

        for (ResourceCategory category : categoryList) {
            // 新建集合，存储是当前分类的资源
            List<Resource> subList = new ArrayList<>();
            for (Resource resource : resourceList) {
                // 判断 资源的分类id 与 分类的id 是否相同
                if(resource.getCategoryId() == category.getId()) {
                    // 相同就添加到新建的集合里面
                    subList.add(resource);
                }
            }
            category.setResourceList(subList);
        }
        return categoryList;
    }
}
