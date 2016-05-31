package com.mycompany.masi.tests;

import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.model.Skill;
import com.mycompany.masi.model.UserAccount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUtils {
private static final Logger LOGGER = LoggerFactory.getLogger(TestUtils.class);
    public static Skill createSkill(){
        Skill skill= new Skill((long)1, "Java", "Język programowania", 40, null, null);
        return skill;
    }
    
    public static JobOffer creteJobOffer(Skill skill) {
        Date now = new Date();
        List<Skill> skills = new ArrayList();
        skills.add(skill);
        JobOffer jobOffer = new JobOffer((long)1, "Programista Java", "Poszukiwany programista Java z dwuletnim doswiadczeniem", skills, "Łódź", now, "Accenture");
        return jobOffer;
    }

    public static UserAccount creteUser() {
        UserAccount userAccount = new UserAccount((long)1, "kowalski", UUID.randomUUID().toString(), "Jan", "Kowalski");
        List<Skill> skills = new ArrayList();
        skills.add(createSkill());
        List<ExternalDocument> externalDocuments = new ArrayList();
        CurriculumVitae curriculumVitae = new CurriculumVitae((long)1, skills, externalDocuments);
        userAccount.setCurriculumVitaes(curriculumVitae);
        return userAccount;
    }

    public static UserAccount creteUserWithExternalDocument() {
        List<String> permission = new ArrayList();
        permission.add("kowalski");
        List<ExternalDocument> externalDocuments = new ArrayList();
        externalDocuments.add(new ExternalDocument((long)1, "Dyplom ukończenia Politechniki Łodzkiej", "Dyplom ukończenia szkoły wyższej", "/document/kowalskiDyplom", permission));
        UserAccount userAccount = new UserAccount((long)1, "kowalski", UUID.randomUUID().toString(), "Jan", "Kowalski");
        List<Skill> skills = new ArrayList();
        skills.add(createSkill( ));
        CurriculumVitae curriculumVitae = new CurriculumVitae((long)1, skills, externalDocuments);
        userAccount.setCurriculumVitaes(curriculumVitae);
        return userAccount;
    }

    static JobApplication createJobApplication() {
        Date now = new Date();
       JobApplication jobApplication=new JobApplication((long) 1, (long) 1, (long) 1, "Jestem zainteresowany ta ofertą", now);
       return jobApplication;
    }
}
