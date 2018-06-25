package com.cap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cmhy on 2018/6/25.
 */
@Controller
public class IndexController {

    @RequestMapping("/testIndex")
    public String testIndex(){
        System.out.println("找到");
        return "index";
    }
}
