package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.JotterArticleDao;
import com.donald.wj_back.pojo.JotterArticle;
import com.donald.wj_back.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Donald
 * @data 21/05/2020 16:44
 */
@Service
public class JotterArticleServiceImpl implements JotterArticleService {
    @Autowired
    private JotterArticleDao jotterArticleDao;
    @Override
    public void addOrUpdate(JotterArticle jotterArticle) {
        jotterArticleDao.save(jotterArticle);
    }
}
