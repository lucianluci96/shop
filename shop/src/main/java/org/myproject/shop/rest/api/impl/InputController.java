package org.myproject.shop.rest.api.impl;

import org.myproject.shop.rest.api.IInputEndpoint;
import org.myproject.shop.rest.dto.InputDto;
import org.myproject.shop.service.api.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
public class InputController implements IInputEndpoint {

    @Autowired
    private IInputService inputService;

    @Override
    public List<InputDto> getInputs() {
        return inputService.getInputs();
    }

    @Override
    public ResponseEntity<InputDto> getInput(@PathVariable long id) {
        if (inputService.getInput(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(inputService.getInput(id), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<InputDto> addInput(@RequestBody InputDto input) {
        inputService.addInput(input);

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }


}



