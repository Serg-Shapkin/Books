package com.books.service.impl;

import com.books.dto.BookDto;
import com.books.entity.Book;
import com.books.exception.BookCreateException;
import com.books.mapper.BookMapper;
import com.books.repository.BookRepository;
import com.books.repository.impl.BookRepositoryImpl;
import com.books.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();

    @Override
    public Book create(BookDto bookDto) {
        if (bookDto.getAuthor() == null || bookDto.getAuthor().isEmpty()) {
            throw new BookCreateException("Необходимо добавить имя автора книги");
        } else if (bookDto.getTitle() == null || bookDto.getTitle().isEmpty()) {
            throw new BookCreateException("Необходимо добавить название книги");
        }
        return bookRepository.create(BookMapper.toBookShort(bookDto));
    }

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Book update(BookDto bookDto) {
        return bookRepository.update(BookMapper.toBook(bookDto));
    }

    @Override
    public void delete(long id) {
        bookRepository.delete(id);
    }
}
