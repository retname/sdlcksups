package com.ordermanager.project.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ordermanager.framework.shiro.service.PasswordService;
import com.ordermanager.project.system.user.domain.User;
import com.ordermanager.project.system.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements IUserService
{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordService passwordService;

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public User selectUserByLoginName(String userName)
    {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     * 
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    @Override
    public User selectUserByPhoneNumber(String phoneNumber)
    {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     * 
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public User selectUserByEmail(String email)
    {
        return userMapper.selectUserByEmail(email);
    }
    /**
     * 修改用户个人详细信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(User user)
    {
        return userMapper.updateUser(user);
    }
}
