package com.donald.wj_back.service;

import com.donald.wj_back.pojo.JotterArticle;
import com.donald.wj_back.utils.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Donald
 * @data 21/05/2020 16:44
 */
public interface JotterArticleService {

    void addOrUpdate(JotterArticle jotterArticle);

    MyPage<JotterArticle> list(int page, int size);

    JotterArticle findById(int id);
    void delete(int id);
}
