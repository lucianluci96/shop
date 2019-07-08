package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.ManagerEntity;
import org.myproject.shop.core.repository.ManagerRepository;
import org.myproject.shop.rest.dto.ManagerDto;
import org.myproject.shop.service.api.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ManagerService implements IManagerService {

    @Autowired
    ManagerRepository managerRepository;

    @Override
    public List<ManagerDto> getManagers() {
        List<ManagerDto> managers = new ArrayList<>();
        for (ManagerEntity managerEntity : managerRepository.findAll())
            managers.add(managerEntity.toDto());

        return managers;
    }

    @Override
    public ManagerDto getManager(long id) {
        if (!managerRepository.exists(id)) {

            return null;
        }

        return (managerRepository.findOne(id).toDto());
    }

    @Override
    public boolean addManager(ManagerDto manager) {
        managerRepository.save(new ManagerEntity().updateFromDto(manager));

        return true;
    }

    @Override
    public boolean updateManager(long id) {
        if (!managerRepository.exists(id)) {

            return false;
        }
        ManagerEntity managerToUpdate = managerRepository.findOne(id);
        managerRepository.save(managerToUpdate);

        return true;
    }

    @Override
    public boolean deleteManager(long id) {
        if (!managerRepository.exists(id)) {
            return false;
        }
        managerRepository.delete(id);

        return true;
    }
}
