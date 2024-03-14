package com.books.service.impl;

import com.books.dto.UserDto;
import com.books.entity.User;
import com.books.mapper.UserMapper;
import com.books.repository.UserRepository;
import com.books.repository.impl.UserRepositoryImpl;
import com.books.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public User create(UserDto userDto) {
        return userRepository.create(UserMapper.toUser(userDto));
    }

    @Override
    public User getById(Integer id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User update(UserDto userDto) {
        return userRepository.update(UserMapper.toUser(userDto));
    }

    @Override
    public User delete(UserDto userDto) {
        return userRepository.delete(UserMapper.toUser(userDto));
    }
}
