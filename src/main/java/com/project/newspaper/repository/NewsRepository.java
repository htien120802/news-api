package com.project.newspaper.repository;

import com.project.newspaper.entity.Category;
import com.project.newspaper.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    public boolean existsByTitle(String title);

    public Page<News> getNewsByCategory(Pageable pageable, Category category);

}
