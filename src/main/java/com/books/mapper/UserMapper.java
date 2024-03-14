package com.books.mapper;

import com.books.dto.UserDto;
import com.books.entity.User;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .build();
    }
    public static User toUser(UserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .age(userDto.getAge())
                .build();
    }
}
