package com.cap.service;

import java.util.List;

import com.cap.entity.ArticleType;

import org.springframework.stereotype.Service;

/**
 * Created by cmhy on 2018/6/22.
 */
@Service
public interface ArticleTypeService {

    List<ArticleType> getAll();
}
