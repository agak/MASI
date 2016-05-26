package com.mycompany.masi.repository;

import com.mycompany.masi.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount, Long> {
 
     UserAccount findByLogin(String login);
}
