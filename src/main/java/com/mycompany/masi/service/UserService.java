package com.mycompany.masi.service;

import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.AdminAccount;
import com.mycompany.masi.model.CompanyAccount;
import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.AccountRepository;
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
public class UserService extends AccountService {

    private final UserAccountRepository userAccountRepository;
    private final CurriculumVitaeRepository curriculumVitaeRepository;
    private final AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(final UserAccountRepository userAccountRepository, final CurriculumVitaeRepository curriculumVitaeRepository, final AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.userAccountRepository = userAccountRepository;
        this.curriculumVitaeRepository = curriculumVitaeRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ExternalDocument> getAllUserExternalDocument(String userLogin) {
        //TODO stworzenie metody to realizującej  w UserAcountRepository
        UserAccount userAccount = userAccountRepository.findOneByLogin(userLogin);
        return userAccount.getCurriculumVitaes().getExternalDocuments();
    }

    public boolean existExternalDocumentByNameForUser(String userLogin, String name) {
        //TODO stworzenie metody to realizującej  w UserAcountRepository
        UserAccount userAccount = userAccountRepository.findOneByLogin(userLogin);
        for (ExternalDocument externalDocument : userAccount.getCurriculumVitaes().getExternalDocuments()) {
            if (externalDocument.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public CurriculumVitae addCv(CurriculumVitae curriculumVitae) {
        return curriculumVitaeRepository.save(curriculumVitae);
    }

    //mockujemy istnienie kont na potrzeby testów- pózniej napisać prawdziwą metode rejestracji
    @PostConstruct
    public void register() {
        Account user = new UserAccount(null, "kowal", passwordEncoder.encode("asdf"), "Jan", "Kowalski");
        Account user2 = new UserAccount(null, "nowak", passwordEncoder.encode("aa"), "Adam", "Nowak");
        Account company = new CompanyAccount(null, "serra", passwordEncoder.encode("qwerty"), "SERRA COMPANY");
        Account admin = new AdminAccount(null, "admin", passwordEncoder.encode("admin"), "Tadeusz", "Wosiak");
        accountRepository.save(user);
        accountRepository.save(user2);
        accountRepository.save(company);
        accountRepository.save(admin);
    }
}
