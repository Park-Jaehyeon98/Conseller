package com.conseller.conseller.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "subCategoryIdx")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subCategoryIdx;

    @Column(name = "sub_category_status", nullable = false)
    private String subCategoryStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "main_category_idx")
    private MainCategory mainCategory;
}
