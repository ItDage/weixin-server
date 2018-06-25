package com.cap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by cmhy on 2018/6/25.
 */
@Controller
public class IndexController {

    @RequestMapping("/testIndex")
    public String testIndex(RedirectAttributes redirectAttributes){
        System.out.println("找到");
        redirectAttributes.addAttribute("type","url");
        redirectAttributes.addAttribute("from","测试啊");
        return "redirect:index.html";
    }
}
