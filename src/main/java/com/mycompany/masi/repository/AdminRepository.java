package com.mycompany.masi.repository;

import com.mycompany.masi.model.AdminAccount;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminAccount, Long> {

    AdminAccount findOneByLogin(String username);

}