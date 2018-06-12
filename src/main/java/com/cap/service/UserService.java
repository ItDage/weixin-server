package com.cap.service;

import java.util.List;

import com.cap.entity.User;

import org.springframework.stereotype.Service;

/**
 * Created by cmhy on 2018/6/11.
 */
@Service
public interface UserService {

    public List<User> getAll();

    public User getUserInfo(String openId);

    int updateUserInfo(User user);

    int insert(User user);
}
