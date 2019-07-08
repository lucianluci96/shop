package org.myproject.shop.rest.api;

import org.myproject.shop.rest.dto.OutputDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/outputs")
public interface IOutputEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<OutputDto> getOutputs();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<OutputDto> getOutput(@PathVariable long id);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<OutputDto> addOutput(@RequestBody OutputDto output);


}
