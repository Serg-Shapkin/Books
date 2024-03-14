package com.books.dto;

import com.books.entity.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class UserDto {
    //private long id;
    private String name;
    private int age;
    //private List<Book> books;
}
