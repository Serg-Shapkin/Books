package com.books.mapper;

import com.books.dto.BookDto;
import com.books.entity.Book;

public class BookMapper {
    public static BookDto toBookDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .title(book.getTitle())
                .user_id(book.getUser_id())
                .build();
    }

    public static Book toBookShort(BookDto bookDto) {
        return Book.builder()
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .user_id(bookDto.getUser_id())
                .build();
    }

    public static Book toBook(BookDto bookDto) {
        return Book.builder()
                .id(bookDto.getId())
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .user_id(bookDto.getUser_id())
                .build();
    }
}
