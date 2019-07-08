package org.myproject.shop.rest.api.impl;

import org.myproject.shop.rest.api.IUserEndpoint;
import org.myproject.shop.rest.dto.UserDto;
import org.myproject.shop.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements IUserEndpoint {


    @Autowired
    IUserService userService;

    @Override
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @Override
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        if (userService.getUser(id)==null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(userService.getUser(id) , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user) {
        userService.addUser(new UserDto());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @Override
    public ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto user) {
        if (!userService.updateUser(id, user)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        UserDto userToUpdate = userService.getUser(id);
        userToUpdate.setUsername(user.getUsername());
        userService.getUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(@PathVariable long id) {
        if (!userService.deleteUser(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}

