package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.Book;
import com.donald.wj_back.pojo.Category;
import com.donald.wj_back.service.BookService;
import com.donald.wj_back.service.CategoryService;
import com.donald.wj_back.utils.MyPage;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Donald
 * @data 13/05/2020 15:28
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class LibraryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FastFileStorageClient storageClient;

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
            return  bookService.listByCategory(cid,pageable);
        }else{
            return  list(pageable);
        }
    }


    @GetMapping("search")
    public Page<Book> searchResult(@RequestParam("keyword") String keyword, @PageableDefault(size = 5,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        if("".equals(keyword)){
            return (Page<Book>) bookService.list(pageable);
        }else{
            return bookService.search(keyword,pageable);
        }
    }

    @GetMapping("categories")
    public List<Category> getCategories(){
        return categoryService.list();
    }

    @PostMapping("covers")
    public String coverUpload(MultipartFile file) {
//        String folder = "G:/ws/data";
//        File imageFolder = new File(folder);
//        File f = new File(imageFolder, StringUtils.getRandomString(6)+file.getOriginalFilename().substring(file.getOriginalFilename().length()-4));
//        if(!f.getParentFile().exists()){
//            f.getParentFile().mkdir();
//        }
        try {
            String originalFilename = file.getOriginalFilename();
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(), file.getSize(), ext, null);
            return "http://www.whitejotter.site:8082/"+storePath.getFullPath();
        }

//        try {
////            file.transferTo(f);
////            String imgURL = "http://localhost:8085/api/file/"+f.getName();
//
//            return imgURL;
//        }

        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
