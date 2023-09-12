package com.project.newspaper.service;

import com.project.newspaper.entity.Category;
import com.project.newspaper.entity.News;
import com.project.newspaper.helper.LogHelper;
import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.project.newspaper.helper.Constant.BASE_URL;
import static com.project.newspaper.helper.Constant.LOG_URL;

@Service
public class CrawlService {
    @Autowired
    CategoryService categoryService;

    @Autowired
    NewsService newsService;

    @PostConstruct
    public void initialCategories() {
        categoryService.initialCategories();
    }
    @Scheduled(cron = "0 */30 * * * ?")
    public void crawlData(){

        String url = BASE_URL+ "tin-moi-nhat.htm"; // Replace with the actual URL to crawl

        try {

            Document doc = Jsoup.connect(url).get();

            Elements elements = doc.getElementsByClass("box-category-item");

            for (Element element : elements){
                News news = new News();
                //Day
                news.setDatePosted(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(element.getElementsByClass("time-ago-last-news").attr("title")));



                //Title
                news.setTitle(element.getElementsByClass("box-category-link-title").attr("title"));

                //Sub content
                news.setSubContent(element.getElementsByClass("box-category-sapo need-trimline").text());

                //Category
                news.setCategory(categoryService.getCategoryByName(element.getElementsByClass("box-category-category").attr("title")));

                //Context path
                //System.out.println(element.getElementsByClass("box-category-link-with-avatar img-resize").attr("href"));

                //Content
                url = BASE_URL + element.getElementsByClass("box-category-link-with-avatar img-resize").attr("href");
                doc = Jsoup.connect(url).get();
                Element content = doc.getElementsByClass("detail-cmain").first();
                content.getElementsByAttribute("href").removeAttr("href");
                news.setContent(content.outerHtml());

                //Source image
                Element temp = element.getElementsByClass("box-category-link-with-avatar img-resize").get(0);
                temp.getAllElements().get(1).attr("class","rounded-t-lg w-full");
                news.setImgSrc(temp.html());

                newsService.createNews(news);
           }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
