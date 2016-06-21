package com.mycompany.masi.service;

import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.AccountRepository;
//import com.mycompany.masi.repository.AccountRepository;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public abstract class AccountService {
//     @Inject
//         private  AccountRepository accountRepository;
    
//     @Inject
//    public AccountService (final AccountRepository accountRepository){
//        this.accountRepository=accountRepository;
//    }
//        public AccountService (){
//            System.out.println("CA");
//    }

    public abstract Account getLogUser(String login) ;
     

}
