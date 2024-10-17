package com.wallet.bank.book.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BookRequest {
    private int rank;
    private int rank_last_week;
    private int weeks_on_list;
    private int asterisk;
    private int dagger;
    private String primary_isbn10;
    private String primary_isbn13;
    private String publisher;
    private String description;
    private String price;
    private String title;
    private String author;
    private String contributor;
    private String contributor_note;
    private String book_image;
    private int book_image_width;
    private int book_image_height;
    private String amazon_product_url;
    private String age_group;
    private String book_review_link;
    private String first_chapter_link;
    private String sunday_review_link;
    private String article_chapter_link;
    private List<Isbn> isbns;
    private List<BuyLink> buy_links;
    private String book_uri;
}
