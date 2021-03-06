package com.mycompany.masi.tests;

import com.mycompany.masi.Application;
import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.repository.JobOffersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.model.Skill;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.CurriculumVitaeRepository;
import com.mycompany.masi.repository.ExternalDocumentRepository;
import com.mycompany.masi.repository.SkillRepository;
import com.mycompany.masi.repository.UserAccountRepository;
import java.util.List;
import org.junit.Assert;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Category(IntegrationTestInterface.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
@IntegrationTest("server.port:0")
public class IntegrationTests {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private JobOffersRepository jobOffersRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ExternalDocumentRepository externalDocumentRepository;
    
      @Autowired
    private UserAccountRepository userAccountRepository;
      
            @Autowired
    private CurriculumVitaeRepository curriculumVitaeRepository;

    private JobOffer jobOffer;
    private Skill skill;
    private JobApplication jobApplication;
    private CurriculumVitae curriculumVitae;
    private ExternalDocument externalDocument;
    private UserAccount userAccount;

    private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationTests.class);

    private final RestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setUp() {
        LOGGER.info("setUp");
        skill = TestUtils.createSkill();
        jobOffer = TestUtils.creteJobOffer(skill);
        jobApplication = TestUtils.createJobApplication();
        curriculumVitae = TestUtils.createCurriculumVitae();
        externalDocument = TestUtils.createExternalDocument();
        userAccount= TestUtils.createUserAccount();
        

        //jobOffersRepository.deleteAll();
        //  skillRepository.deleteAll();
        skillRepository.save(skill);
        externalDocumentRepository.save(externalDocument);
        jobOffersRepository.save(jobOffer);
        curriculumVitaeRepository.save(curriculumVitae);
        userAccountRepository.save(userAccount);
        //TODO save jobApplication and curriculumVitae and other??
    }

    @Test
    public void canGetAllJobOffer() {
        String URI = "http://localhost:" + port + "/jobOffers/getAll";

        ResponseEntity<List<JobOffer>> rateResponse
                = restTemplate.exchange(URI,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<JobOffer>>() {
                });

        List<JobOffer> givenJobOfferList = rateResponse.getBody();
        Assert.assertEquals(1, givenJobOfferList.size());
        Assert.assertEquals(jobOffer.getName(), givenJobOfferList.get(0).getName());
        Assert.assertEquals(jobOffer.getDescription(), givenJobOfferList.get(0).getDescription());
        Assert.assertEquals(jobOffer.getIdJobOffer(), givenJobOfferList.get(0).getIdJobOffer());
        Assert.assertEquals(jobOffer.getIndustry(), givenJobOfferList.get(0).getIndustry());
        //Assert.assertEquals(jobOffer.getInsertDate(), givenJobOfferList.get(0).getInsertDate());
        Assert.assertEquals(jobOffer.getLocation(), givenJobOfferList.get(0).getLocation());
        Assert.assertEquals(skill.getCategory(), givenJobOfferList.get(0).getSkills().get(0).getCategory());
        Assert.assertEquals(skill.getName(), givenJobOfferList.get(0).getSkills().get(0).getName());
        Assert.assertEquals(skill.getPoints(), givenJobOfferList.get(0).getSkills().get(0).getPoints());
        Assert.assertEquals(skill.getIdSkill(), givenJobOfferList.get(0).getSkills().get(0).getIdSkill());
    }

    @Test
    public void canGetJobOfferById() {
        long jobOfferId = 1;
        String URI = "http://localhost:" + port + "/jobOffers/getOne?jobOfferId=" + jobOfferId;

        JobOffer givenJobOffer = restTemplate.getForObject(URI, JobOffer.class);
        Assert.assertEquals(jobOffer.getName(), givenJobOffer.getName());
        Assert.assertEquals(jobOffer.getDescription(), givenJobOffer.getDescription());
        Assert.assertEquals(jobOffer.getIdJobOffer(), givenJobOffer.getIdJobOffer());
        Assert.assertEquals(jobOffer.getIndustry(), givenJobOffer.getIndustry());
        Assert.assertEquals(jobOffer.getLocation(), givenJobOffer.getLocation());
        Assert.assertEquals(skill.getCategory(), givenJobOffer.getSkills().get(0).getCategory());
        Assert.assertEquals(skill.getName(), givenJobOffer.getSkills().get(0).getName());
        Assert.assertEquals(skill.getPoints(), givenJobOffer.getSkills().get(0).getPoints());
        Assert.assertEquals(skill.getIdSkill(), givenJobOffer.getSkills().get(0).getIdSkill());
    }

    @Test
    public void canAddJobOffer() {
        String URI = "http://localhost:" + port + "/jobOffers/add";

        JobOffer givenJobOffer = restTemplate.postForObject(URI, jobOffer, JobOffer.class);
        Assert.assertEquals(jobOffer.getName(), givenJobOffer.getName());
        Assert.assertEquals(jobOffer.getDescription(), givenJobOffer.getDescription());
        Assert.assertEquals(jobOffer.getIdJobOffer(), givenJobOffer.getIdJobOffer());
        Assert.assertEquals(jobOffer.getIndustry(), givenJobOffer.getIndustry());
        Assert.assertEquals(jobOffer.getInsertDate(), givenJobOffer.getInsertDate());
        Assert.assertEquals(jobOffer.getLocation(), givenJobOffer.getLocation());
        Assert.assertEquals(skill.getCategory(), givenJobOffer.getSkills().get(0).getCategory());
        Assert.assertEquals(skill.getName(), givenJobOffer.getSkills().get(0).getName());
        Assert.assertEquals(skill.getPoints(), givenJobOffer.getSkills().get(0).getPoints());
        Assert.assertEquals(skill.getIdSkill(), givenJobOffer.getSkills().get(0).getIdSkill());
    }

    @Test
    public void canjJobApply() {
        long userId = 1;
        long jobOfferId = 1;
        String userMessage = "Jestem zainteresowany ta ofertą";
        String URI = "http://localhost:" + port + "/jobOffers/jobApply?login=" + userAccount.getLogin() 
                + "&jobOfferId=" + jobOfferId + "&userMessage=" + userMessage;

        JobApplication givenJobApplication = restTemplate.getForObject(URI, JobApplication.class);
        Assert.assertEquals(jobApplication.getLogin(), givenJobApplication.getLogin());
        Assert.assertEquals(jobApplication.getIdJobOffer(), givenJobApplication.getIdJobOffer());
        Assert.assertEquals(jobApplication.getIdJobApplication(), givenJobApplication.getIdJobApplication());
        Assert.assertEquals(jobApplication.getUserMessage(), givenJobApplication.getUserMessage());
    }

    @Test
    public void canAddCv() {
        String URI = "http://localhost:" + port + "/user/addCv?login="+userAccount.getLogin();

        CurriculumVitae givenCurriculumVitae = restTemplate.postForObject(URI, curriculumVitae, CurriculumVitae.class);

        Assert.assertEquals(curriculumVitae.getIdCurriculumVitae(), givenCurriculumVitae.getIdCurriculumVitae());
        Assert.assertEquals(curriculumVitae.getExternalDocuments().size(), givenCurriculumVitae.getExternalDocuments().size());
        Assert.assertEquals(curriculumVitae.getExternalDocuments().get(0).getIdExternalDocument(), 
                givenCurriculumVitae.getExternalDocuments().get(0).getIdExternalDocument());
        Assert.assertEquals(curriculumVitae.getExternalDocuments().get(0).getLocationUrl(), 
                givenCurriculumVitae.getExternalDocuments().get(0).getLocationUrl());
        Assert.assertEquals(curriculumVitae.getExternalDocuments().get(0).getName(),
                givenCurriculumVitae.getExternalDocuments().get(0).getName());
        Assert.assertEquals(curriculumVitae.getExternalDocuments().get(0).getType(), 
                givenCurriculumVitae.getExternalDocuments().get(0).getType());
        Assert.assertEquals(curriculumVitae.getExternalDocuments().get(0).getPermission().get(0), 
                givenCurriculumVitae.getExternalDocuments().get(0).getPermission().get(0));
        Assert.assertEquals(curriculumVitae.getSkills().size(), givenCurriculumVitae.getSkills().size());
        Assert.assertEquals(skill.getCategory(), givenCurriculumVitae.getSkills().get(0).getCategory());
        Assert.assertEquals(skill.getName(), givenCurriculumVitae.getSkills().get(0).getName());
        Assert.assertEquals(skill.getPoints(), givenCurriculumVitae.getSkills().get(0).getPoints());
        Assert.assertEquals(skill.getIdSkill(), givenCurriculumVitae.getSkills().get(0).getIdSkill());
    }
}
