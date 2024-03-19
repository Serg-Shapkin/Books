package com.books.repository.impl;

import com.books.connection.ConnectionService;
import com.books.entity.Book;
import com.books.repository.BookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public Book create(Book book) {
        String sql = "insert into books(author, title, user_id) values (?, ?, ?)";
        System.out.println("Запрошено добавление книги " + book.getTitle());

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setLong(3, book.getUser_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return book;
    }

    @Override
    public Book getById(long id) {
        Book book = new Book();
        String sql = "select * from books where id = ?";
        System.out.println("Запрошена книга с id: " + id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                book = Book.builder()
                        .id(resultSet.getLong("id"))
                        .author(resultSet.getString("author"))
                        .title(resultSet.getString("title"))
                        .user_id(resultSet.getLong("user_id"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "select * from books";
        System.out.println("Запрошен список всех книг");

        try (Connection connection = ConnectionService.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = Book.builder()
                        .id(resultSet.getLong("id"))
                        .author(resultSet.getString("author"))
                        .title(resultSet.getString("title"))
                        .user_id(resultSet.getLong("user_id"))
                        .build();

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (books.isEmpty()) {
            System.out.println("Список книг пуст");
        }
        return books;
    }

    @Override
    public Book update(Book book) {
        String sql = "update books set author=?, title=?, user_id=? where id=?";
        System.out.println("Запрошено обновление для книги " + book.getTitle());

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setLong(3, book.getUser_id());
            preparedStatement.setLong(4, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return book;
    }

    @Override
    public void delete(long id) {
        String sql = "delete from books where id=?";
        System.out.println("Запрошено удаление книги с id: " + id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
