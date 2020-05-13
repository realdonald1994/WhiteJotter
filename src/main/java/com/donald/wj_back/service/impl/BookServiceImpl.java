package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.BookDao;
import com.donald.wj_back.dao.CategoryDao;
import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import com.donald.wj_back.service.BookService;
import com.donald.wj_back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:20
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private CategoryService categoryService;

    @Override
    public List<Book> list() {
        return bookDao.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(Book book) {
        bookDao.save(book);
    }

    @Override
    public void deleteById(int id) {
        bookDao.deleteById(id);
    }

    @Override
    public List<Book> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category);
    }
}
