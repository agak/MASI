package com.mycompany.masi.repository;

import com.mycompany.masi.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);

}