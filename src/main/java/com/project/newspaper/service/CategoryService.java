package com.project.newspaper.service;

import com.project.newspaper.entity.Category;
import com.project.newspaper.repository.CategoryRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.project.newspaper.helper.Constant.BASE_URL;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public void initialCategories() {
        String url = BASE_URL;
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByClass("menu-nav").first().getElementsByTag("a");

            for (int i = 2; i < elements.size()-1; i++){
                Category category = new Category();
                category.setName(elements.get(i).attr("title"));
                category.setCode(elements.get(i).attr("href").replace("/","").replace(".htm",""));
                this.createCategory(category);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public String createCategory(Category category){
        if (!categoryRepository.existsByCode(category.getCode())){
            categoryRepository.save(category);
            return "Create category successfully!";
        }
        return "This category is already exist!";
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryByName(String name){
        return categoryRepository.getByName(name);
    }
}
