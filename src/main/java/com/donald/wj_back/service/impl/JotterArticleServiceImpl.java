package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.JotterArticleDao;
import com.donald.wj_back.pojo.JotterArticle;
import com.donald.wj_back.redis.RedisService;
import com.donald.wj_back.service.JotterArticleService;
import com.donald.wj_back.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Set;

/**
 * @author Donald
 * @data 21/05/2020 16:44
 */
@Service
public class JotterArticleServiceImpl implements JotterArticleService {
    @Autowired
    private JotterArticleDao jotterArticleDao;

    @Autowired
    private RedisService redisService;


    @Override
    public void addOrUpdate(JotterArticle jotterArticle) {
        jotterArticle.setArticleDate(new Date());
        jotterArticleDao.save(jotterArticle);
        // 删除当前选中的文章和所有文章页面的缓存
        redisService.delete("article" + jotterArticle.getId());
        Set<String> keys = redisService.getKeysByPattern("articlepage*");
        redisService.delete(keys);
    }

    @Override
    public MyPage<JotterArticle> list(int page, int size) {
        MyPage<JotterArticle> articles;
        // 用户访问列表页面时按页缓存文章
        String key = "articlepage:" + page;
        Object articlePageCache = redisService.get(key);
        if(articlePageCache==null){
            Page<JotterArticle> articlesInDb = jotterArticleDao.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));
            articles = new MyPage<JotterArticle>(articlesInDb);
            redisService.set(key,articles);
        }else{
            articles=(MyPage<JotterArticle>)articlePageCache;
        }
        return articles;
    }

    @Override
    public JotterArticle findById(int id) {
        return jotterArticleDao.findById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        jotterArticleDao.deleteById(id);
        // 删除当前选中的文章和所有文章页面的缓存
        redisService.delete("article:" + id);
        Set<String> keys = redisService.getKeysByPattern("articlepage*");
        redisService.delete(keys);
    }
}
