package org.myproject.shop.core.repository;


import org.myproject.shop.core.model.ManagerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends CrudRepository<ManagerEntity, Long> {
    List<ManagerEntity> findByLastName(String lastName);
}
