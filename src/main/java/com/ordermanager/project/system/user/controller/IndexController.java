package com.ordermanager.project.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import com.ordermanager.framework.web.controller.BaseController;
import com.ordermanager.project.system.menu.domain.Menu;
import com.ordermanager.project.system.menu.service.IMenuService;
import com.ordermanager.project.system.user.domain.User;

@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap)
    {
        // 取身份信息
        User user = getSysUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus", menus);
        mmap.put("user", user);
        return "index";
    }
}
