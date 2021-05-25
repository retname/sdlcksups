package com.ordermanager.project.system.menu.service;

import java.util.List;
import java.util.Set;
import com.ordermanager.project.system.menu.domain.Menu;
import com.ordermanager.project.system.user.domain.User;


public interface IMenuService
{
    /**
     * 根据用户ID查询菜单
     * 
     * @param user 用户信息
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUser(User user);
    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);
}
