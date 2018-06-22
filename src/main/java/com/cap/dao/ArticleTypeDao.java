package com.cap.dao;

import java.util.List;

import com.cap.entity.ArticleType;

import org.apache.ibatis.annotations.Mapper;

/**
 * Created by cmhy on 2018/6/22.
 */
@Mapper
public interface ArticleTypeDao {

    List<ArticleType> getAll();
}
