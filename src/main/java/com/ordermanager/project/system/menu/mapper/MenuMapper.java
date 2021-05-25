package com.ordermanager.project.system.menu.mapper;

import java.util.List;
import com.ordermanager.project.system.menu.domain.Menu;

public interface MenuMapper
{
    /**
     * 查询系统正常显示菜单（不含按钮）
     * 
     * @return 菜单列表
     */
    public List<Menu> selectMenuNormalAll();
    
    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<Menu> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectPermsByUserId(Long userId);
}
