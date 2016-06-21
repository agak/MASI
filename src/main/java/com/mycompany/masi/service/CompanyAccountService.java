package com.mycompany.masi.service;

import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.AdminAccount;
import com.mycompany.masi.model.CompanyAccount;
import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.AccountRepository;
import com.mycompany.masi.repository.CompanyAccountRepository;
import com.mycompany.masi.repository.CurriculumVitaeRepository;
import com.mycompany.masi.repository.UserAccountRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Validated
public class CompanyAccountService extends AccountService {

    private final CompanyAccountRepository companyAccountRepository;

    @Autowired
    public CompanyAccountService(final CompanyAccountRepository companyAccountRepository) {
        this.companyAccountRepository=companyAccountRepository;
    }

   
    @Override
    public Account getLogUser(String login) {
    return companyAccountRepository.findOneByLogin(login) ;}
}
