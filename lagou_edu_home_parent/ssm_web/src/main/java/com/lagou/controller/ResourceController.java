package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /*
        查询所有资源分类（条件查询）
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo) {
        PageInfo<Resource> pageInfo = resourceService.findAllResource(resourceVo);

        return new ResponseResult(true, 200, "资源信息分页多条件成功", pageInfo);

    }

    /*
        新增&修改资源
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource) {
        // 这里可以通过session获取到当前登录用户的id，查询出当前用户信息，然后将updateBy和createdBy信息封装信息到Resource中
        if(resource.getId() == null) {
            resourceService.saveResource(resource);
            return new ResponseResult(true, 200, "新增资源成功", null);
        } else {
            resourceService.updateResource(resource);
            return new ResponseResult(true,200,"修改资源成功", null);
        }
    }

    /**
     * 回显资源信息
     */
    @GetMapping("/findResourceById")
    public ResponseResult findResourceById(Integer id) {
        Resource resource = resourceService.findResourceById(id);
        return new ResponseResult(true, 200, "回显资源成功", resource);
    }

    /**
     * 删除资源信息
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id) {
        resourceService.deleteResource(id);
        return new ResponseResult(true, 200, "删除资源成功", null);
    }
}
