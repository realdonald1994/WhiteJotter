package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:07
 */
public interface BookDao extends JpaRepository<Book,Integer> {
    Page<Book> findAllByCategory(Category category, Pageable pageable);
    Page<Book> findAllByTitleLikeOrAuthorLike(String title,String author,Pageable pageable);
}
