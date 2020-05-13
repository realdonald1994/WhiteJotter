package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.service.BookService;
import com.donald.wj_back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:28
 */
@RestController
@RequestMapping("/api")
public class LibraryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("books")
    public List<Book> list(){
        return bookService.list();
    }

    @DeleteMapping("delete")
    public void delete(@RequestBody Book book){
        bookService.deleteById(book.getId());
    }

    @PostMapping("books")
    public Book addOrUpdate(@RequestBody Book book){
        bookService.addOrUpdate(book);
        return book;
    }

    @GetMapping("categories/{cid}/books")
    public List<Book> listByCategory(@PathVariable("cid")int cid){
        if( 0!=cid){
            return bookService.listByCategory(cid);
        }else{
            return list();
        }
    }
}
