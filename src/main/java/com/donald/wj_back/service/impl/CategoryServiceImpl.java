package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.CategoryDao;
import com.donald.wj_back.pojo.Category;
import com.donald.wj_back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:12
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> list() {
        return categoryDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    public Category get(int id) {
        return categoryDao.findById(id).orElse(null);
    }
}
