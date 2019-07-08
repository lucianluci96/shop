package org.myproject.shop.core.repository;


import org.myproject.shop.core.model.ShopEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends CrudRepository<ShopEntity, Long> {
    List<ShopEntity> findByName(String name);
}