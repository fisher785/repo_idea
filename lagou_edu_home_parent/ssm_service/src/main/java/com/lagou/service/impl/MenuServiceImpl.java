package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    //查询所有父子菜单信息
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        return menuMapper.findSubMenuListByPid(pid);
    }

    // 根据id查找menu
    @Override
    public Menu findMenuById(Integer id) {
        return menuMapper.findMenuById(id);
    }

    // 查询所有菜单信息
    @Override
    public PageInfo<Menu> findAllMenu(MenuVo menuVo) {
        PageHelper.startPage(menuVo.getCurrentPage(), menuVo.getPageSize());
        List<Menu> list = menuMapper.findAllMenu();
        PageInfo<Menu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    // 新增菜单
    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);

        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menuMapper.saveMenu(menu);
    }

    // 修改菜单
    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedBy("管理员");
        menu.setUpdatedTime(new Date());
        menuMapper.updateMenu(menu);
    }
}
