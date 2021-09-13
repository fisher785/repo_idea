package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;

import java.util.List;

public interface MenuService {


    /*
        查询所有父子菜单信息
     */
    List<Menu> findSubMenuListByPid(int pid);

    /*
        根据id查找menu
     */
    Menu findMenuById(Integer id);

    /*
        查询菜单列表
     */
    PageInfo<Menu> findAllMenu(MenuVo menuVo);

    /*
        新增菜单信息
     */
    void saveMenu(Menu menu);

    /*
        修改菜单
     */
    void updateMenu(Menu menu);
}
