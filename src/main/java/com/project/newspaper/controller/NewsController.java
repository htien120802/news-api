package com.project.newspaper.controller;

import com.project.newspaper.entity.News;
import com.project.newspaper.entity.ResponseModel;
import com.project.newspaper.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class NewsController {
    @Autowired
    private NewsService newsService;
//    @GetMapping("")
//    public ResponseEntity<ResponseModel<List<News>>> getAllNewses(){
//        return ResponseEntity.ok(new ResponseModel<>("success","Get all newses is success",newsService.getAllNews()));
//    }
    @GetMapping(value = "/news/category/{code}",params = {"page","size"})
    public ResponseEntity<ResponseModel<List<News>>> getNewsesByCategoryCode(@RequestParam("page") int page, @RequestParam("size") int size, @PathVariable("code") String code){
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(new ResponseModel<>("success","Get newses is success",newsService.getNewsByCategoryCode(pageable,code)));

    }
    @GetMapping("/news/{id}")
    public ResponseEntity<ResponseModel<News>> getNewsById(@PathVariable("id") Long id){
        Optional<News> news = newsService.getNewsById(id);
        if (news.isPresent()){
            return ResponseEntity.ok(new ResponseModel<>("success","Get news successfully",news.get()));
        }else {
            return ResponseEntity.ok(new ResponseModel<>("failure","Can't find news with this id",null));
        }
    }
    @CrossOrigin
    @GetMapping(path = "/news",params = {"page","size"})
    public ResponseEntity<ResponseModel<List<News>>> getPage(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "id"));
        return ResponseEntity.ok(new ResponseModel<>("success","Successfully",newsService.getNewsPagable(pageable).getContent()));
    }
}
