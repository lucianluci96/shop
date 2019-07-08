package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.UserEntity;
import org.myproject.shop.core.repository.UserRepository;
import org.myproject.shop.rest.dto.UserDto;
import org.myproject.shop.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> users = new ArrayList<>();
        for (UserEntity shopEntity : userRepository.findAll())
            users.add(shopEntity.toDto());

        return users;
    }

    @Override
    public UserDto getUser(long id) {
        if (!userRepository.exists(id)) {

            return null;
        }

        return (userRepository.findOne(id).toDto());
    }

    @Override
    public boolean addUser(UserDto user) {
        userRepository.save(new UserEntity().updateFromDto(user));

        return true;
    }

    @Override
    public boolean updateUser(long id, UserDto userDto) {
        if (!userRepository.exists(id)) {

            return false;
        }
        UserEntity userToUpdate = userRepository.findOne(id);
        userRepository.save(userToUpdate);

        return true;
    }

    @Override
    public boolean deleteUser(long id) {
        if (!userRepository.exists(id)) {

            return false;
        }
        userRepository.delete(id);

        return true;
    }
}

