package com.project.newspaper.service;

import com.project.newspaper.entity.News;
import com.project.newspaper.repository.CategoryRepository;
import com.project.newspaper.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<News> getAllNews(){
        return newsRepository.findAll();
    }

    public String createNews(News news){
        if (newsRepository.existsByTitle(news.getTitle()))
            return "This rews is already in database!!!";
        else {
            newsRepository.save(news);
            return "Create news is sucessful!";
        }
    }

    public List<News> getNewsByCategoryCode(Pageable pageable, String code){
        return newsRepository.getNewsByCategory(pageable,categoryRepository.findByCode(code)).getContent();
    }

    public Optional<News> getNewsById(Long id){
        return newsRepository.findById(id);
    }

    public Page<News> getNewsPagable(Pageable pageable){
        return newsRepository.findAll(pageable);
    }
}
