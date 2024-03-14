package com.books.dto;

import com.books.entity.Book;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BookDto {
    private long id;
    private String author;
    private String description;
    private long user_id;
}
