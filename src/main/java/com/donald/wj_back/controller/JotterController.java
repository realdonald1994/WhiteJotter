package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.JotterArticle;
import com.donald.wj_back.service.JotterArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Donald
 * @data 21/05/2020 16:45
 */
@RestController
@RequestMapping("/api")
public class JotterController {
    @Autowired
    private JotterArticleService jotterArticleService;

    @PostMapping("/admin/content/article")
    public ResponseEntity<String> saveArticle(@RequestBody @Valid JotterArticle jotterArticle){
        jotterArticleService.addOrUpdate(jotterArticle);
        return ResponseEntity.ok("Save Successfully");
    }
}
