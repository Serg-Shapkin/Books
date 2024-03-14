package com.books.service.impl;

import com.books.dto.BookDto;
import com.books.entity.Book;
import com.books.mapper.BookMapper;
import com.books.repository.BookRepository;
import com.books.repository.impl.BookRepositoryImpl;
import com.books.service.BookService;

import java.util.List;

public class BookServiceImp implements BookService {

    private final BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public Book create(BookDto bookDto) {
        return bookRepository.create(BookMapper.toBookShort(bookDto)); // short - без id
    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book update(BookDto bookDto) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
