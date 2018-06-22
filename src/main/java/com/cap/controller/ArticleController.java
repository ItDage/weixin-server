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
    public Result insertList(String requestUrl, int type){
        Result result = new Result();
        articleService.insertList(requestUrl, type);
        return result;
    }

    @ResponseBody
    @RequestMapping("/get")
    public Result get(int pageNum){
        Result result = new Result();
        List<Article> list = articleService.getArticleByPageNum(pageNum * 10);
        //查所有
        List<Article> list2 = articleService.getArticleByPageNum(null);
        result.setData(list);
        result.setCode(list2.size());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        Result result = new Result();
        System.out.println(article);
        article.setArticleAbstract(article.getContent().substring(0, article.getContent().length() / 3));
        articleService.add(article);
        return result;
    }
}
