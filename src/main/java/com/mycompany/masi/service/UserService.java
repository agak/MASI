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
        //TODO stworzenie metody to realizującej  w UserAcountRepository
        UserAccount userAccount= userAccountRepository.findByLogin(userLogin);
        return userAccount.getCurriculumVitaes().getExternalDocuments();
    }
    
    public boolean existExternalDocumentByNameForUser(String userLogin, String name){
         //TODO stworzenie metody to realizującej  w UserAcountRepository
         UserAccount userAccount= userAccountRepository.findByLogin(userLogin);
         for (ExternalDocument externalDocument : userAccount.getCurriculumVitaes().getExternalDocuments()) {
            if(externalDocument.getName().equals(name)){
             return true;
            }
        }
        return false;
    }
}
