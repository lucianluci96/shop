package org.myproject.shop.core.repository;

import org.myproject.shop.core.model.InputEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InputRepository extends CrudRepository<InputEntity, Long> {

}
