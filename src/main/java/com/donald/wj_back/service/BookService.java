package com.donald.wj_back.service;

import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.utils.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Donald
 * @data 13/05/2020 15:11
 */
public interface BookService {
    Page<Book> list(Pageable pageable);

    void addOrUpdate(Book book);

    void deleteById(int id);

    Page<Book> listByCategory(int cid, Pageable pageable);

    Page<Book> search(String keyword, Pageable pageable);
}
