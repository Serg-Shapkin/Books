package com.books.repository.impl;

import com.books.connection.ConnectionService;
import com.books.entity.Book;
import com.books.repository.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book create(Book book) {
        String sql = "insert into books(author, description, user_id) values (?, ?, ?)";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setLong(3, book.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
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
    public Book update(Book book) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
