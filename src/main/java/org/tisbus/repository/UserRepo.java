package org.tisbus.repository;

import org.springframework.data.repository.CrudRepository;
import org.tisbus.entity.UserEntity;

public interface UserRepo extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
