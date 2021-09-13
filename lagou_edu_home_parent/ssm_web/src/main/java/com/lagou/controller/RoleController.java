package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*
        查询所有角色（条件）
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role) {
        List<Role> list = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "查询所有角色成功", list);
    }

    /*
        新增&修改角色
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setState(200);
        responseResult.setSuccess(true);
        responseResult.setContent("");
        if (role.getId() == null) {
            roleService.saveRole(role);
            responseResult.setMessage("新增角色成功");
        } else {
            roleService.updateRole(role);
            responseResult.setMessage("修改角色信息成功");
        }
        return responseResult;
    }

    /**
     * 根据id查询角色信息（回显）
     */
    @GetMapping("/findRoleById")
    public ResponseResult findRoleById(Integer id) {
        Role role = roleService.findRoleById(id);
        return new ResponseResult(true, 200, "查询角色成功", role);
    }

    /*
        查询所有的父子菜单信息（分配菜单的第一个接口）
     */
    @Autowired
    private MenuService menuService;

    @RequestMapping("/findAllMenu")
    public ResponseResult findMenuByRoleId() {
        // -1表示查询所有父子级菜单
        List<Menu> list = menuService.findSubMenuListByPid(-1);

        // 响应数据
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", list);

        return new ResponseResult(true, 200, "查询父子菜单成功", map);
    }

    /*
        根据角色id查询关联的菜单id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {
        List<Integer> list = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true, 200, "查询角色对应的菜单id成功", list);
    }


    /*
        为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult roleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {
        roleService.roleContextMenu(roleMenuVo);
        return new ResponseResult(true, 200, "响应成功", "");
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {
        roleService.deleteRole(id);
        return new ResponseResult(true, 200, "删除角色成功", "");
    }

    @Autowired
    private ResourceCategoryService categoryService;

    /*
        根据角色id查询资源分类及资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(Integer roleId) {
        List<ResourceCategory> list = categoryService.findResourceCategoryWithResourceByRoleId(roleId);
        return new ResponseResult(true, 200, "查找所有资源数据成功", list);
    }

    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo, HttpServletRequest request) {
        roleService.roleContextResource(roleResourceVo, request);
        return new ResponseResult(true, 200, "为角色分配资源成功", null);
    }

}
