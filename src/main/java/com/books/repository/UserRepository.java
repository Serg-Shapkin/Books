package com.books.repository;

import com.books.entity.User;

import java.util.List;

public interface UserRepository {
    User create(User user);
    User getById(Integer id);
    List<User> getAll();
    User update(User user);
    User delete(User user);
}
