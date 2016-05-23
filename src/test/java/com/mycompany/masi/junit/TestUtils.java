package com.mycompany.masi.junit;

import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.model.Skill;
import com.mycompany.masi.model.UserAccount;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestUtils {

    public static JobOffer creteJobOffer() {
        Date now = new Date();
        List<Skill> skills = new ArrayList();
        skills.add(new Skill(UUID.randomUUID().toString(), "Java", "Język programowania", 40, null, null));
        JobOffer jobOffer = new JobOffer(UUID.randomUUID().toString(), "Programista Java", "Poszukiwany programista Java z dwuletnim doswiadczeniem", skills, "Łódź", now, "Accenture");
        return jobOffer;
    }

    public static UserAccount creteUser() {
        List<String> permission = new ArrayList();
        permission.add("kowalski");
        List<ExternalDocument> externalDocuments = new ArrayList();
        //externalDocuments.add(new ExternalDocument(UUID.randomUUID().toString(), "Dyplom ukończenia Politechniki Łodzkiej", "Dyplom ukończenia szkoły wyższej", "/document/kowalskiDyplom", permission));
        List<Skill> skills = new ArrayList();
        skills.add(new Skill( UUID.randomUUID().toString(), "Java", "Język programowania", 40, null, null));
        CurriculumVitae curriculumVitae = new CurriculumVitae(UUID.randomUUID().toString(), skills, externalDocuments);
        UserAccount userAccount = new UserAccount("Jan", "Kowalski", new Date(1990, 1, 1), curriculumVitae);
        return userAccount;
    }
}
