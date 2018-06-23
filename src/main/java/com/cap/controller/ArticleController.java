package com.cap.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        list.forEach(article -> {
            String s = sf.format(article.getPublishDate());
            Date date = null;
            try {
                date = sf.parse(s);
                article.setPublishDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
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
        article.setArticleAbstract(article.getContent().replace("<p>", "").replace("</p>", "。").substring(0,article.getContent().length()/4));
        int flag = articleService.add(article);
        if(flag > 0){
            result.setCode(200);
        }else {
            result.setCode(404);
        }
        return result;
    }
}
