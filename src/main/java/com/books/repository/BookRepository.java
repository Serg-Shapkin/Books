package com.books.repository;

import com.books.entity.Book;

import java.util.List;

public interface BookRepository {
    Book create(Book book);
    Book getById(long id);
    List<Book> getAll();
    Book update(Book book);
    void delete(long id);
}
