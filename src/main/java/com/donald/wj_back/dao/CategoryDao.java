package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 13/05/2020 15:08
 */
public interface CategoryDao extends JpaRepository<Category,Integer> {

}
