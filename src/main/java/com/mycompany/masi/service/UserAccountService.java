package com.mycompany.masi.service;

import com.mycompany.masi.controller.JobOffersController;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountService.class);

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

    public ExternalDocument uploadFile(MultipartFile file) {
        ExternalDocument externalDocument = null;
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                String[] partsiFileName = fileName.split("\\.");
               // String newFileName = partsiFileName[0] + RandomStringUtils.randomAlphanumeric(6) + "." + partsiFileName[1];
                String newFileName = partsiFileName[0] +"." + partsiFileName[1];
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(newFileName)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();
                LOGGER.info("You successfully uploaded ", file.getOriginalFilename());
                externalDocument = new ExternalDocument(null, newFileName, null, newFileName, null);
                externalDocumentRepository.save(externalDocument);

            } catch (Exception e) {
                LOGGER.info("You failed to upload ", new Object[]{file.getOriginalFilename(), e.getMessage()});
            }
        } else {
            LOGGER.info("You failed to upload  because the file was empty", file.getOriginalFilename());
        }
        return externalDocument;
    }
}
