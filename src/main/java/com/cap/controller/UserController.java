package com.cap.controller;

import java.util.List;

import com.cap.entity.Result;
import com.cap.entity.User;
import com.cap.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by scy on 2018/6/11.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/getAll")
    public void getUser(){
        List<User> list = userService.getAll();
        list.forEach(user -> {
            System.out.println(user.getUsername());
        });
    }

    @ResponseBody
    @RequestMapping("/getUserInfo")
    public User getUserInfo(String openId){
        return userService.getUserInfo(openId);
    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.PUT)
    public Result updateUserInfo(@RequestBody User user){
        Result result = new Result();
        result.setCode(userService.updateUserInfo(user));
        return result;
    }
}
