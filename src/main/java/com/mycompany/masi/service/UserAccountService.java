package com.mycompany.masi.service;

import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.AdminAccount;
import com.mycompany.masi.model.CompanyAccount;
import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.LifeEvent;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.AccountRepository;
import com.mycompany.masi.repository.CurriculumVitaeRepository;
import com.mycompany.masi.repository.ExternalDocumentRepository;
import com.mycompany.masi.repository.LifeEventRepository;
import com.mycompany.masi.repository.SkillRepository;
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
public class UserAccountService extends AccountService {

    private final UserAccountRepository userAccountRepository;
    private final CurriculumVitaeRepository curriculumVitaeRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final SkillRepository skillRepository;
    private final LifeEventRepository lifeEventRepository;
    private final ExternalDocumentRepository externalDocumentRepository;

    @Autowired
    public UserAccountService(final UserAccountRepository userAccountRepository, final CurriculumVitaeRepository curriculumVitaeRepository, final AccountRepository accountRepository, final PasswordEncoder passwordEncoder,
            final SkillRepository skillRepository, final LifeEventRepository lifeEventRepository, ExternalDocumentRepository externalDocumentRepository) {
        this.userAccountRepository = userAccountRepository;
        this.curriculumVitaeRepository = curriculumVitaeRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.skillRepository = skillRepository;
        this.lifeEventRepository = lifeEventRepository;
        this.externalDocumentRepository = externalDocumentRepository;

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

    public CurriculumVitae addCv(CurriculumVitae curriculumVitae, String login) {
        CurriculumVitae curriculumVitaeDatabase = null;
        LifeEvent lifeEventDatabase = null;
        if (curriculumVitae.getIdCurriculumVitae() != null) {
            curriculumVitaeDatabase = curriculumVitaeRepository.findOne(curriculumVitae.getIdCurriculumVitae());
        }
        if (curriculumVitae.getLifeEvents().get(0).getIdLifeEvent() != null) {
            lifeEventDatabase = lifeEventRepository.findOne(curriculumVitae.getLifeEvents().get(0).getIdLifeEvent());
        }
        //skillRepository.save(curriculumVitae.getSkills());
        externalDocumentRepository.save(curriculumVitae.getExternalDocuments());
        lifeEventRepository.save(curriculumVitae.getLifeEvents());
        if (!(curriculumVitaeDatabase != null && lifeEventDatabase != null)) {
            curriculumVitaeRepository.save(curriculumVitae);
        }
        UserAccount userAccount = userAccountRepository.findOneByLogin(login);
        userAccount.setCurriculumVitaes(curriculumVitae);
        userAccountRepository.save(userAccount);
        return curriculumVitae;
    }

    //mockujemy istnienie kont na potrzeby testów- pózniej napisać prawdziwą metode rejestracji
    @PostConstruct
    public void register() {
        Account user = new UserAccount(null, "kowal", passwordEncoder.encode("asdf"), "Jan", "Kowalski");
        Account user2 = new UserAccount(null, "nowak", passwordEncoder.encode("aa"), "Adam", "Nowak");
        Account user3 = new UserAccount(null, "malinowski", passwordEncoder.encode("qq"), "Grzegorz", "Malinowski");
        Account user4 = new UserAccount(null, "jankowska", passwordEncoder.encode("zz"), "Alina", "Jankowska");
        Account company = new CompanyAccount(null, "serra", passwordEncoder.encode("qwerty"), "SERRA COMPANY");
        Account admin = new AdminAccount(null, "admin", passwordEncoder.encode("admin"), "Tadeusz", "Wosiak");
        accountRepository.save(user);
        accountRepository.save(user2);
        accountRepository.save(user3);
        accountRepository.save(user4);
        accountRepository.save(company);
        accountRepository.save(admin);
    }

    @Override
    public Account getLogUser(String login) {
        return userAccountRepository.findOneByLogin(login);
    }

    public UserAccount editUser(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    public CurriculumVitae getCvByUser(String login) {
        CurriculumVitae curriculumVitae = null;
        UserAccount userAccount = userAccountRepository.findOneByLogin(login);
        if (userAccount.getCurriculumVitaes() != null) {
            curriculumVitae = curriculumVitaeRepository.findOne(userAccount.getCurriculumVitaes().getIdCurriculumVitae());
        }
        return curriculumVitae;
    }
}
