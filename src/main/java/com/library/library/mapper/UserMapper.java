package com.library.library.mapper;

import com.library.library.domain.User;
import com.library.library.domain.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public User mapToUser(final UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getCreationDate()
        );
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getCreationDate()
        );
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList) {
        return userList.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
