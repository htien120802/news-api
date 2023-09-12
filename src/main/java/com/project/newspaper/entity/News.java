package com.project.newspaper.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.Text;

import java.util.Date;

@Entity
@Table
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String title;
    @Column(columnDefinition = "TEXT")
    private String subContent;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(columnDefinition = "TEXT")
    private String imgSrc;
    private Date datePosted;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
