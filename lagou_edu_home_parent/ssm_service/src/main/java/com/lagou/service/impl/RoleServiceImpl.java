package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.ResourceCategoryService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    // 查询角色
    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    // 添加角色
    @Override
    public void saveRole(Role role) {
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        if (role.getCreatedBy() == null) role.setCreatedBy("unknown");
        if (role.getUpdatedBy() == null) role.setUpdatedBy("unknown");
        roleMapper.saveRole(role);
    }

    // 根据id查询角色信息
    @Override
    public Role findRoleById(int id) {
        return roleMapper.findRoleById(id);
    }

    // 修改角色信息
    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());
        if (role.getUpdatedBy() == null) role.setUpdatedBy("unknown");
        roleMapper.updateRole(role);
    }

    // 查询
    @Override
    public List<Integer> findMenuByRoleId(int roleId) {
        return roleMapper.findMenuByRoleId(roleId);
    }

    // 为角色分配菜单
    @Override
    public void roleContextMenu(RoleMenuVo roleMenuVo) {
        // 清空中间表的关系
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        // 为角色分配菜单
        for (Integer mid : roleMenuVo.getMenuIdList()) {

            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setMenuId(mid);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            // 封装数据
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);

            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            roleMapper.roleContextMenu(role_menu_relation);
        }
    }

    // 删除角色
    @Override
    public void deleteRole(Integer rid) {
        // 根据rid清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(rid);
        roleMapper.deleteRole(rid);
    }

    @Autowired
    private UserMapper userMapper;
    /*
        为角色分配资源
     */
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo, HttpServletRequest request) {
        // 清空中间表
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());
        
        // 为角色分配菜单
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {
            Role_resource_relation role_resource_relation = new Role_resource_relation();
            role_resource_relation.setResourceId(resourceId);
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());

            Date date = new Date();
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);

            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");
//            Integer userId = (Integer) request.getSession().getAttribute("user_id");
//            User currentUser = userMapper.findUserById(userId);
//            role_resource_relation.setCreatedBy(currentUser.getName());
//            role_resource_relation.setUpdatedBy(currentUser.getName());

            roleMapper.roleContextResource(role_resource_relation);
        }
    }


}
