package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.BookDao;
import com.donald.wj_back.dao.CategoryDao;
import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import com.donald.wj_back.redis.RedisService;
import com.donald.wj_back.service.BookService;
import com.donald.wj_back.service.CategoryService;
import com.donald.wj_back.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.CastUtils;
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

    @Autowired
    private RedisService redisService;

    @Override
    public MyPage<Book> list(Pageable pageable) {
        MyPage<Book> books;
        String key = "booklist";
        Object bookCache = redisService.get(key);

        if(bookCache ==null){
            Page<Book> booksInDb = bookDao.findAll(pageable);
            books = (MyPage<Book>) booksInDb;
            redisService.set(key,books);
        }else {
            books = (MyPage<Book>)bookCache;
        }
        return books;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(Book book) {
        redisService.delete("booklist");
        bookDao.save(book);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("booklist");
    }

    @Override
    public void deleteById(int id) {
        redisService.delete("booklist");
        bookDao.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("booklist");
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
