package org.myproject.shop.rest.api;


import org.myproject.shop.rest.dto.ManagerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/managers")
public interface IManagerEndpoint {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<ManagerDto> getManagers();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<ManagerDto> getManager(@PathVariable long id);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<ManagerDto> addManager(@RequestBody ManagerDto manager);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<ManagerDto> deleteManager(@PathVariable long id);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<ManagerDto> updateManager(@PathVariable long id, @RequestBody ManagerDto manager);
}
