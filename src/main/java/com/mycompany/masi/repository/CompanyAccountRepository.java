package com.mycompany.masi.repository;

import com.mycompany.masi.model.CompanyAccount;
import org.springframework.data.repository.CrudRepository;

public interface CompanyAccountRepository extends CrudRepository<CompanyAccount, Long> {
 
     CompanyAccount findOneByLogin(String login);
}
