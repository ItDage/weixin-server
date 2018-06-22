package com.cap.controller;

import java.util.List;

import com.cap.entity.ArticleType;
import com.cap.entity.Result;
import com.cap.service.ArticleTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cmhy on 2018/6/22.
 */
@Controller
@RequestMapping("/articleType")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService articleTypeService;

    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(){
        Result result = new Result();
        List<ArticleType> list = articleTypeService.getAll();
        System.out.println(list);
        result.setData(list);
        return result;
    }
}
