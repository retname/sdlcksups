package com.ordermanager.project.system.role.mapper;


public interface RoleMenuMapper
{
    
    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int selectCountRoleMenuByMenuId(Long menuId);
}
