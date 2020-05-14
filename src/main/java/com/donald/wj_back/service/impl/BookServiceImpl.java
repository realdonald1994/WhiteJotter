package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.BookDao;
import com.donald.wj_back.dao.CategoryDao;
import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import com.donald.wj_back.service.BookService;
import com.donald.wj_back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Book> list(Pageable pageable) {
        return bookDao.findAll(pageable);
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
    public Page<Book> listByCategory(int cid,Pageable pageable) {
        Category category = categoryService.get(cid);
        return bookDao.findAllByCategory(category,pageable);
    }

    @Override
    public Page<Book> search(String keyword,Pageable pageable) {
        return bookDao.findAllByTitleLikeOrAuthorLike("%"+keyword+"%","%"+keyword+"%",pageable);
    }
}
