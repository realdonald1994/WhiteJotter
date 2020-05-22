package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.JotterArticle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 21/05/2020 16:42
 */
public interface JotterArticleDao extends JpaRepository<JotterArticle,Integer> {
    JotterArticle findById(int id);
}
