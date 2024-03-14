package com.books.mapper;

import com.books.dto.BookDto;
import com.books.entity.Book;

public class BookMapper {
    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .description(book.getDescription())
                .information(book.getInformation())
                .build();
    }

    public static Book toBookDto(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .author(bookDto.getAuthor())
                .description(bookDto.getDescription())
                .information(bookDto.getInformation())
                .build();
    }
}
