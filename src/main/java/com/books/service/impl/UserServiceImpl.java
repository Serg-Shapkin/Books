package com.books.service.impl;

import com.books.dto.UserDto;
import com.books.entity.User;
import com.books.exception.UserCreateException;
import com.books.mapper.UserMapper;
import com.books.repository.UserRepository;
import com.books.repository.impl.UserRepositoryImpl;
import com.books.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User create(UserDto userDto) {
        if (userDto.getName() == null || userDto.getName().isEmpty()) {
            throw new UserCreateException("Необходимо добавить имя пользователя");
        } else if (userDto.getAge() <= 0 || userDto.getAge() > 120) {
            throw new UserCreateException("Необходимо корректно указать возраст пользователя");
        }
        return userRepository.create(UserMapper.toUserShort(userDto));
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User update(UserDto userDto) {
        return userRepository.update(UserMapper.toUser(userDto)); // DTO с id
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }
}
