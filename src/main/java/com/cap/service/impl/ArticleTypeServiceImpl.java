package com.cap.service.impl;

import java.util.List;

import com.cap.dao.ArticleDao;
import com.cap.dao.ArticleTypeDao;
import com.cap.entity.ArticleType;
import com.cap.service.ArticleService;
import com.cap.service.ArticleTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cmhy on 2018/6/22.
 */
@Service
public class ArticleTypeServiceImpl implements ArticleTypeService{

    @Autowired
    private ArticleTypeDao articleTypeDao;

    @Override
    public List<ArticleType> getAll() {
        return articleTypeDao.getAll();
    }
}
