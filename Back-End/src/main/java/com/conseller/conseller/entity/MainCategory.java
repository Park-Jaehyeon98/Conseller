package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "mainCategoryIdx")

public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mainCategoryIdx;

    @Column(name = "main_category_content", nullable = false)
    private String mainCategoryContent;
}
