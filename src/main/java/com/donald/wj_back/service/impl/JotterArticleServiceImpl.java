package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.JotterArticleDao;
import com.donald.wj_back.pojo.JotterArticle;
import com.donald.wj_back.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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
        jotterArticle.setArticleDate(new Date());
        jotterArticleDao.save(jotterArticle);
    }

    @Override
    public Page<JotterArticle> list(int page, int size) {
        return jotterArticleDao.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"id")));
    }

    @Override
    public JotterArticle findById(int id) {
        return jotterArticleDao.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        jotterArticleDao.deleteById(id);
    }
}
