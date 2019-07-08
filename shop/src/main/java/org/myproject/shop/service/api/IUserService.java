package org.myproject.shop.service.api;

import java.util.List;

import org.myproject.shop.rest.dto.UserDto;

public interface IUserService {
    List<UserDto> getUsers();

    UserDto getUser(long id);

    boolean addUser(UserDto userDto);

    boolean updateUser(long id, UserDto user);

    boolean deleteUser(long id);
}

