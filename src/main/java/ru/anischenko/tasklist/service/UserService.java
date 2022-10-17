package ru.anischenko.tasklist.service;

import ru.anischenko.tasklist.model.dto.UserDto;

public interface UserService {
    void createUser(UserDto userDto);
}
