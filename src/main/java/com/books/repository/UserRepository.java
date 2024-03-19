package com.books.repository;

import com.books.entity.User;

import java.util.List;

public interface UserRepository {
    User create(User user);

    User getById(long id);

    List<User> getAll();

    User update(User user);

    void delete(long id);
}
