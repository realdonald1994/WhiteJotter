package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.JotterArticle;
import com.donald.wj_back.service.JotterArticleService;
import com.donald.wj_back.utils.MyPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Donald
 * @data 21/05/2020 16:45
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class JotterController {
    @Autowired
    private JotterArticleService jotterArticleService;

    @PostMapping("/admin/content/article")
    public ResponseEntity<String> saveArticle(@RequestBody @Valid JotterArticle jotterArticle){
        jotterArticleService.addOrUpdate(jotterArticle);
        return ResponseEntity.ok("Save Successfully");
    }

    @GetMapping("/article/{size}/{page}")
    public ResponseEntity<MyPage<JotterArticle>> listArticles(@PathVariable("size")int size, @PathVariable("page") int page){
        return ResponseEntity.ok(jotterArticleService.list(page-1,size));
    }

    @GetMapping("/article/{id}")
    public ResponseEntity<JotterArticle> getOneArticle(@PathVariable("id")int id){
        return ResponseEntity.ok(jotterArticleService.findById(id));
    }

    @DeleteMapping("/admin/content/article/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable("id") int id){
        jotterArticleService.delete(id);
        return ResponseEntity.ok("Delete Successfully");
    }
}
