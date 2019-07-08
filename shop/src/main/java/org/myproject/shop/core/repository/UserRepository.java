package org.myproject.shop.core.repository;

import org.myproject.shop.core.model.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);
}
