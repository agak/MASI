package com.mycompany.masi.repository;

import com.mycompany.masi.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
 
   // Account findOneByLogin(String login);
}
