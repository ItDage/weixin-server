package com.cap.service.impl;

import java.util.List;

import com.cap.dao.ArticleDao;
import com.cap.entity.Article;
import com.cap.service.ArticleService;
import com.cap.util.HttpResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cmhy on 2018/6/21.
 */
@Service
public class ArticleServiceImpl  implements ArticleService{

    @Autowired
    private ArticleDao articleDao;
    @Override
    public int insertList(String url) {
        List<Article> list = HttpResource.getNews(url);
        return articleDao.insertList(list);
    }

    @Override
    public List<Article> getArticleByPageNum(int pageNum) {
        return articleDao.getArticleByPageNum(pageNum);
    }
}