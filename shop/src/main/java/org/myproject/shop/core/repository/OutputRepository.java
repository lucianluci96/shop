package org.myproject.shop.core.repository;


import org.myproject.shop.core.model.OutputEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutputRepository extends CrudRepository<OutputEntity, Long> {

}
