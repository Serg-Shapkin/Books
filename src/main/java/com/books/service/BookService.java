package com.books.service;

import com.books.dto.BookDto;
import com.books.entity.Book;

import java.util.List;

public interface BookService {
    Book create(BookDto bookDto);
    Book getById(long id);
    List<Book> getAll();
    Book update(BookDto bookDto);
    void delete(long id);
}
