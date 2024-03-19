package com.books.repository.impl;

import com.books.connection.ConnectionService;
import com.books.entity.Book;
import com.books.entity.User;
import com.books.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User create(User user) {
        String sql = "insert into users(name, age) values (?, ?)";
        System.out.println("Запрошено добавление пользователя " + user.getName());

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getById(long id) {
        User user = new User();
        String sql = "select * from users where id = ?";
        System.out.println("Запрошен пользователь с id: " + id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .age(resultSet.getInt("age"))
                        .build();

                user.setBooks(addBooks(user.getId()));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "select * from users";
        System.out.println("Запрошен список всех пользователей");

        try (Connection connection = ConnectionService.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .age(resultSet.getInt("age"))
                        .build();

                user.setBooks(addBooks(user.getId()));

                users.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст");
        }
        return users;
    }

    @Override
    public User update(User user) {
        String sql = "update users set name=?, age=? where id=?";
        System.out.println("Запрошено обновление данных пользователя " + user.getName());

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void delete(long id) {
        String sql = "delete from users where id=?";
        System.out.println("Запрошено удаление книги с id: " + id);

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Book> addBooks(long id) {
        List<Book> books = new ArrayList<>();
        Book book;
        String sql = "select b.id, author, title, user_id from users AS u join books AS b on u.id = b.user_id where u.id = ?";

        try (Connection connection = ConnectionService.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                book = Book.builder()
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
        return books;
    }
}
