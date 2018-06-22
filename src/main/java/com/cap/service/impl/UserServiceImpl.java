package com.cap.service.impl;

import java.util.List;

import com.cap.dao.UserDao;
import com.cap.entity.User;
import com.cap.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by scy on 2018/6/11.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User getUserInfo(String openId) {
        return userDao.getUserInfo(openId);
    }

    @Override
    public int updateUserInfo(User user) {
        System.out.println("此时的session_key" + user.getSession_key());
        User userInfo = getUserInfo(user.getOpenId());
        return  userInfo == null ? insert(user) : userDao.updateUserInfo(user);
    }

    @Override
    public int insert(User user) {
        return userDao.insert(user);
    }
}
