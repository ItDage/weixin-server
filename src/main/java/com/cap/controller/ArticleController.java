package com.cap.controller;

import java.util.List;

import com.cap.entity.Article;
import com.cap.entity.Result;
import com.cap.service.ArticleService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by scy on 2018/6/21.
 * @Description 文章有关的接口
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @ResponseBody
    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public Result insertList(String requestUrl){
        Result result = new Result();
        articleService.insertList(requestUrl);
        return result;
    }

    @ResponseBody
    @RequestMapping("/get")
    public Result get(int pageNum){
        Result result = new Result();
//        articleService.insertList();
        List<Article> list = articleService.getArticleByPageNum(pageNum);
        System.out.println(list.size());
        System.out.println(list);
        result.setData(list);
        return result;
    }
}
