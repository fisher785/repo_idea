package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.User;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
        查询所有资源分类
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();

        return new ResponseResult(true, 200, "查询资源分类信息成功", list);
    }


    @Autowired
    private UserService userService;

    /*
        添加&修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory, HttpServletRequest request) {

        // 用户处于登录状态，session中有userId，这里通过user_id查询到用户，将修改人/创建人准确传递
//        User currentUser = userService.findUserById((Integer) request.getSession().getAttribute("user_id"));
//        String username = currentUser.getName();

        // 判断是否传递了id值
        if(resourceCategory.getId() == null) {

            // 封装参数
//            resourceCategory.setCreatedBy(username);
//            resourceCategory.setUpdatedBy(username);

            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true, 200,"新增资源分类成功","");
        } else {
//            resourceCategory.setUpdatedBy(username);
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true, 200,"修改资源分类成功","");
        }
    }

    /*
        根据id删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id) {
        resourceCategoryService.deleteResourceCategoryById(id);
        return new ResponseResult(true, 200, "删除资源分类成功", "");
    }


}
