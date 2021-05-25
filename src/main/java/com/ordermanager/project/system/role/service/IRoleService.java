package com.ordermanager.project.system.role.service;

import java.util.List;
import java.util.Set;
import com.ordermanager.project.system.role.domain.Role;

public interface IRoleService
{
    /**
     * 根据条件分页查询角色数据
     * 
     * @param role 角色信息
     * @return 角色数据集合信息
     */
    public List<Role> selectRoleList(Role role);

    /**
     * 根据用户ID查询角色
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRoleKeys(Long userId);
}
