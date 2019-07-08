package org.myproject.shop.rest.api.impl;

import org.myproject.shop.rest.api.IManagerEndpoint;
import org.myproject.shop.rest.dto.ManagerDto;
import org.myproject.shop.service.api.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class ManagerController implements IManagerEndpoint {


    @Autowired
    private IManagerService managerService;

    @Override
    public List<ManagerDto> getManagers() {
        return managerService.getManagers();
    }

    @Override
    public ResponseEntity<ManagerDto> getManager(@PathVariable long id) {
        if (managerService.getManager(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return new ResponseEntity<>(managerService.getManager(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ManagerDto> addManager(@RequestBody ManagerDto manager) {
        managerService.addManager(new ManagerDto());

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @Override
    public ResponseEntity<ManagerDto> deleteManager(@PathVariable long id) {
        if (!managerService.deleteManager(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        managerService.deleteManager(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @Override
    public ResponseEntity<ManagerDto> updateManager(@PathVariable long id, @RequestBody ManagerDto manager) {
        if (!managerService.updateManager(id)) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ManagerDto managerToUpdate = managerService.getManager(id);
        managerToUpdate.setFirstName(manager.getFirstName());
        managerToUpdate.setLastName(manager.getLastName());
        managerService.getManager(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

}