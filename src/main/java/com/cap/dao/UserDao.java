package com.cap.dao;

import java.util.List;

import com.cap.entity.User;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by scy on 2018/6/11.
 */
@Mapper
public interface UserDao {

    List<User> getAll();

    User getUserInfo(String openId);

    int updateUserInfo(User user);

    int insert(User user);
}
