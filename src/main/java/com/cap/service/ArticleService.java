package com.cap.service;

import java.util.List;

import com.cap.entity.Article;

import org.springframework.stereotype.Service;

/**
 * Created by scy on 2018/6/21.
 */
@Service
public interface ArticleService {

    int insertList(String requestUrl, int type);

    List<Article> getArticleByPageNum(Integer pageNum);

}
