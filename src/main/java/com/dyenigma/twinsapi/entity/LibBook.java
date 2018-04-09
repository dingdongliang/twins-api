package com.dyenigma.twinsapi.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * twins/com.dyenigma.twinsapi.entity
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/4/9 11:38
 */
@Getter
@Setter
public class LibBook extends BaseDomain {
    private String bookId;

    private String bookTitle;

    private String categoryId;

    private String author;

    private Integer pages;

    private BigDecimal price;

    private String isbn;

    private String publisher;

    private Integer editionYear;

    private String bookImg;

    private String bookDesc;

    private String status;
}