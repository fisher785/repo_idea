package com.lagou.service;

import com.lagou.domain.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleService {

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
    List<Integer> findMenuByRoleId(int roleId);

    /*
        为角色分配菜单信息
     */
    void roleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    void deleteRole(Integer rid);

    /*
        为角色分配菜单信息
     */
    void roleContextResource(RoleResourceVo roleResourceVo, HttpServletRequest request);

}
