package com.cap.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.cap.entity.Result;
import com.cap.entity.User;
import com.cap.service.impl.UserServiceImpl;
import com.google.gson.Gson;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by scy on 2018/6/11.
 */
@Controller
public class LoginController {

    @Value("${weixin.auth.url}")
    private String url;
    @Autowired
    private UserServiceImpl userServiceImpl;

    public static ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<String, String>();

    @ResponseBody
    @RequestMapping("/login")
    public Result testLogin(String code){
        Result result = new Result();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String requestUrl = url;
        requestUrl += ("&js_code=" + code);
        HttpGet httpGet = new HttpGet(requestUrl);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                //请求成功
                String str = EntityUtils.toString(response.getEntity());
                Gson gson = new Gson();
                Map map = gson.fromJson(str, Map.class);
                String openId = (String)map.get("openid");
                String session_key = (String)map.get("session_key");
                User user = new User();
                user.setOpenId(openId);
                user.setSession_key(session_key);
                userServiceImpl.updateUserInfo(user);
                System.out.println("session_key" + session_key);
                result.setData(openId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
