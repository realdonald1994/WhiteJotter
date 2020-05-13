package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:07
 */
public interface BookDao extends JpaRepository<Book,Integer> {
    List<Book> findAllByCategory(Category category);
    List<Book> findAllByTitleLikeOrAuthorLike(String title,String author);
}
