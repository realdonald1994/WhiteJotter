package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import com.donald.wj_back.service.BookService;
import com.donald.wj_back.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Page<Book> list(@PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC) Pageable pageable){
        return bookService.list(pageable);
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
    public Page<Book> listByCategory(@PathVariable("cid")int cid, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        if( 0!=cid){
            return bookService.listByCategory(cid,pageable);
        }else{
            return list(pageable);
        }
    }


    @GetMapping("search")
    public Page<Book> searchResult(@RequestParam("keyword") String keyword, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        if("".equals(keyword)){
            return bookService.list(pageable);
        }else{
            return bookService.search(keyword,pageable);
        }
    }

    @GetMapping("categories")
    public List<Category> getCategories(){
        return categoryService.list();
    }
}
