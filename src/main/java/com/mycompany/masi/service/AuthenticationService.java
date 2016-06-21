package com.mycompany.masi.service;

import com.mycompany.masi.controller.JobOffersController;
import com.mycompany.masi.exception.AccountNotFoundException;
import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.CompanyAccount;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.AccountRepository;
import com.mycompany.masi.repository.CompanyAccountRepository;
import com.mycompany.masi.repository.UserAccountRepository;
import com.mycompany.masi.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class AuthenticationService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationService.class);
    
  @Autowired
    private UserRepository repository;

      @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private CompanyAccountRepository companyAccountRepository;

  
    @Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
            LOGGER.info("Username in loadUserByUsername "+ username);
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        UserAccount userAccount = userAccountRepository.findOneByLogin(username);
        CompanyAccount companyAccount = companyAccountRepository.findOneByLogin(username);
        
        String login = "";
        String password = "";
        if (userAccount != null) {
            setAuths.add(new SimpleGrantedAuthority("USER_ROLE"));
            login = userAccount.getLogin();
            password = userAccount.getPassword();
        } else if (companyAccount != null) {
            setAuths.add(new SimpleGrantedAuthority("COMPANY_ROLE"));
            login = companyAccount.getLogin();
            password = companyAccount.getPassword();
        } else {
            throw new AccountNotFoundException(username);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(setAuths);
        return new User(login, password,
                true, true, true, true, authorities);
    }
}
