package com.project.newspaper.controller;

import com.project.newspaper.entity.Category;
import com.project.newspaper.entity.ResponseModel;
import com.project.newspaper.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public ResponseEntity<ResponseModel<List<Category>>> initialCategories(){
        return ResponseEntity.ok(new ResponseModel<>("success","Get categories is success", categoryService.getAllCategories()));
    }
}
