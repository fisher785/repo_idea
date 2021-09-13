package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
        回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id) {


        List<Menu> menuList = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        // 根据id判断当前是更新还是添加操作，id是否为-1
        if(id == -1) {
            // 添加操作，回显信息中不需要menu信息
            // 封装数据
            map.put("menuInfo", null);
            map.put("parentMenuList",menuList);
            return new ResponseResult(true, 200, "添加回显成功", map);
        } else {
            // 修改操作
            Menu menu = menuService.findMenuById(id);
            // 封装数据
            map.put("menuInfo", menu);
            map.put("parentMenuList",menuList);
            return new ResponseResult(true, 200, "修改回显成功", map);
        }
    }

    /*
        查询菜单列表信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuVo menuVo) {
        PageInfo<Menu> list = menuService.findAllMenu(menuVo);
        return new ResponseResult(true, 200, "查询所有菜单成功", list);
    }

    /*
        新增&修改菜单
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu) {
        if(menu.getId() == null) {
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"新增菜单成功", null);
        } else {
            menuService.updateMenu(menu);
            return new ResponseResult(true, 200, "修改菜单成功", null);
        }
    }
}
