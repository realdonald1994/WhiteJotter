package com.donald.wj_back.service;

import com.donald.wj_back.pojo.Book;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:11
 */
public interface BookService {
    List<Book> list();

    void addOrUpdate(Book book);

    void deleteById(int id);

    List<Book> listByCategory(int cid);
}
