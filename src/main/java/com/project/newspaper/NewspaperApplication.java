package com.project.newspaper;

import com.project.newspaper.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class NewspaperApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewspaperApplication.class, args);
    }

}
