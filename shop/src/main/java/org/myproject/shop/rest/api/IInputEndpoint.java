package org.myproject.shop.rest.api;


import org.myproject.shop.rest.dto.InputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@RequestMapping(value = "/inputs")
public interface IInputEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<InputDto> getInputs();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<InputDto> getInput(@PathVariable long id);

    @RequestMapping(value = "", method = RequestMethod.POST)
    ResponseEntity<InputDto> addInput(@RequestBody InputDto input);
}
