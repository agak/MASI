package com.mycompany.masi.service;

import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.UserAccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService extends AccountService{
    
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserService(final UserAccountRepository userAccountRepository){
        this.userAccountRepository=userAccountRepository;
    }
  
    public List<ExternalDocument> getAllUserExternalDocument(String userLogin){
        UserAccount userAccount= userAccountRepository.findByLogin(userLogin);
        return userAccount.getCurriculumVitaes().getExternalDocuments();
    }
    
}
