package com.project.newspaper.repository;

import com.project.newspaper.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public boolean existsByCode(String code);
    public Category getByName(String name);
    public Category findByCode(String code);
}
