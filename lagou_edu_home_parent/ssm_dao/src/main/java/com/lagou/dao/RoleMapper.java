package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件查询
     */
    List<Role> findAllRole(Role role);

    /*
        添加角色
     */
    void saveRole(Role role);

    /*
        回显（根据id查询角色）
     */
    Role findRoleById(int id);

    /*
        修改角色
     */
    void updateRole(Role role);

    /*
        根据角色id查询该角色关联的菜单信息id
     */
    List<Integer> findMenuByRoleId(int id);

    /*
        根据roleId清空中间表的关联关系
     */
    void deleteRoleContextMenu(Integer rid);

    /*
        为角色分配菜单信息
     */
    void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    void deleteRole(Integer roleId);

    /*
        根据roleId清空角色资源中间表的关联关系
     */
    void deleteRoleContextResource(Integer rid);

    /*
        为角色分配资源信息
     */
    void roleContextResource(Role_resource_relation role_resource_relation);


}
