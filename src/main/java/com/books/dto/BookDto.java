package com.books.dto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookDto {
    private long id;
    private String author;
    private String title;
    private long user_id;
}
