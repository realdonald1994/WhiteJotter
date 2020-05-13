package com.donald.wj_back.service;

import com.donald.wj_back.pojo.Category;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:11
 */
public interface CategoryService {

    List<Category> list();

    Category get(int id);
}
