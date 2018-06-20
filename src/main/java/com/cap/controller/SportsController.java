package com.cap.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.cap.entity.Result;
import com.cap.entity.Sport;
import com.cap.entity.StepInfo;
import com.cap.entity.User;
import com.cap.service.UserService;
import com.cap.service.impl.SportsServiceImpl;
import com.cap.util.WXCore;
import com.google.gson.Gson;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cmhy on 2018/6/20.
 */
@Controller
public class SportsController {

    @Value("${weixin.appId}")
    private String appId;
    @Autowired
    private SportsServiceImpl sportsService;
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/sports/getSteps", method = RequestMethod.POST)
    public Result getSteps(@RequestBody Sport sport){
        Result result = new Result();
        User user = userService.getUserInfo(sport.getOpenId());
        List<StepInfo> list = sportsService.getStepInfo(appId, sport.getEncryptedData(), user.getSession_key(), sport.getIv());
        result.setMsg("获取成功!");
        result.setData(list);
        return result;
    }
}
