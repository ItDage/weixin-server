package com.cap.dao;

import java.util.List;

import com.cap.entity.Article;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by cmhy on 2018/6/21.
 */
@Mapper
public interface ArticleDao {

    int insertList(List<Article> list);

    List<Article> getArticleByPageNum(Integer pageNum);

    int add(Article article);
}
