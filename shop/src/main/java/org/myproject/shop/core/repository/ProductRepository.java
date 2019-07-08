package org.myproject.shop.core.repository;

import org.myproject.shop.core.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity,Long>{
    List<ProductEntity> findByName(String name);
}
