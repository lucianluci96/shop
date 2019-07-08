package org.myproject.shop.rest.api;

import org.myproject.shop.rest.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/users")
public interface IUserEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<UserDto> getUsers();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<UserDto> getUser(@PathVariable long id);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<UserDto> addUser(@RequestBody UserDto user);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<UserDto> updateUser(@PathVariable long id, @RequestBody UserDto user);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<UserDto> deleteUser(@PathVariable long id);


}

