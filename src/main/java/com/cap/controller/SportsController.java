package com.cap.controller;

import com.cap.entity.Result;
import com.cap.util.WXCore;
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

    @ResponseBody
    @RequestMapping(value = "/sports/getSteps", method = RequestMethod.POST)
    public Result getSteps(@RequestBody String encryptedData, @RequestBody String iv, @RequestBody String openId){
        System.out.println("encrypteData" + encryptedData + "iv" + iv + "openId" + openId);
        Result result = new Result();
        String session_key = LoginController.map.get(openId);
        System.out.println(WXCore.decrypt(appId, encryptedData, session_key, iv));
        result.setMsg("获取成功!");
        return result;
    }
}