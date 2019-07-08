package org.myproject.shop.rest.api.impl;

import org.myproject.shop.rest.api.IOutputEndpoint;
import org.myproject.shop.rest.dto.OutputDto;
import org.myproject.shop.service.api.IOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class OutputController implements IOutputEndpoint {

    @Autowired
    private IOutputService outputService;


    @Override
    public List<OutputDto> getOutputs() {
        return outputService.getOutputs();
    }


    @Override
    public ResponseEntity<OutputDto> getOutput(@PathVariable long id) {
        if (outputService.getOutput(id)==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(outputService.getOutput(id) , HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OutputDto> addOutput(@RequestBody OutputDto output) {
        outputService.addOutput(output);;

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}


