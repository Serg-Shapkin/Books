package com.books.service;

import com.books.dto.UserDto;
import com.books.entity.User;

import java.util.List;

public interface UserService {
    User create(UserDto userDto);
    User getById(long id);
    List<User> getAll();
    User update(UserDto userDto);
    void delete(long id);
}
