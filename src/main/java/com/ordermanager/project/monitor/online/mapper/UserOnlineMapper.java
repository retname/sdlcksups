package com.ordermanager.project.monitor.online.mapper;

import com.ordermanager.project.monitor.online.domain.UserOnline;

import java.util.List;

public interface UserOnlineMapper
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectOnlineById(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public int deleteOnlineById(String sessionId);

    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     * @return 会话集合
     */
    public List<UserOnline> selectOnlineByExpired(String lastAccessTime);
}
