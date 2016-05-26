package com.mycompany.masi.tests;

import com.mycompany.masi.exception.JobOfferNotFoundException;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.model.Skill;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.JobOffersRepository;
import com.mycompany.masi.repository.SkillRepository;
import com.mycompany.masi.repository.UserAccountRepository;
import com.mycompany.masi.service.JobOffersService;
import com.mycompany.masi.service.UserService;
import java.util.List;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JunitTests {

    @Mock
    private JobOffersRepository jobOffersRepository;
    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    SkillRepository skillRepository;

    private JobOffersService jobOffersService;
    private UserService userService;

    @Before
    public void setUp() {
        jobOffersService = new JobOffersService(jobOffersRepository, skillRepository);
        userService = new UserService(userAccountRepository);
    }

    @Test
    public void shouldAddJobOffer_TheAddedJobOfferShouldBeReturned() {
        final Skill skill = TestUtils.createSkill();
        final JobOffer jobOffer = TestUtils.creteJobOffer(skill);
        final JobOffer addedJobOffer = stubRepositoryToReturnAddedJobOffer(jobOffer);
        final JobOffer returnedJobOffer = jobOffersService.addJobOffer(jobOffer);

        // verify repository was called with document
        verify(jobOffersRepository, times(1)).save(jobOffer);
        assertEquals("Returned job offer should be the same as added job ofer", addedJobOffer, returnedJobOffer);
    }

    @Test
    public void shouldGetJobOfferById_JobOfferShouldBeReturned() {
        final long jobOfferId = 1;
        final JobOffer receivedJobOffer = stubRepositoryToReturnOneJobOffer(jobOfferId);
        final JobOffer returnedJobOffer = jobOffersService.getJobOfferById(jobOfferId);

        // verify repository was called with document
        verify(jobOffersRepository, times(1)).findByIdJobOffer(jobOfferId);
        assertEquals("Returned job offer should be the same as received job ofer", receivedJobOffer, returnedJobOffer);
    }

    @Test(expected = JobOfferNotFoundException.class)
    public void shouldGetJobOfferById_JobOfferNotFound_TheExceptionShouldBeThrown() {
        final long jobOfferId = 1;
        jobOffersService.getJobOfferById(jobOfferId);

        // verify repository was called with document
        verify(jobOffersRepository, times(1)).findByIdJobOffer(jobOfferId);
    }

    @Test
    public void shouldGetUserExternalDocuments_ListShouldBeNull() {
        String userLogin = "kowalski";
        stubRepositoryToReturnUser(userLogin);
        List<ExternalDocument> externalDocuments = userService.getAllUserExternalDocument(userLogin);

        // verify repository was called with document
        verify(userAccountRepository, times(1)).findByLogin(userLogin);
        assertThat("Returned external documents list should be null", externalDocuments, is(empty()));
    }

    @Test
    public void shouldCheckUserExternalDocumentExist_FalseShouldBeReturned() {
        String userLogin = "kowalski";
        String name = "Świadectwo maturalne";
        stubRepositoryToReturnUserWithExternalDocument(userLogin);
        boolean returnValue = userService.existExternalDocumentByNameForUser(userLogin, name);

        // verify repository was called with document
        verify(userAccountRepository, times(1)).findByLogin(userLogin);
        assertFalse("Returned value should be false", returnValue);
    }

    private JobOffer stubRepositoryToReturnAddedJobOffer(JobOffer jobOffer) {
        //final JobOffer jobOffer=TestUtils.creteJobOffer();
        when(jobOffersRepository.save(jobOffer)).thenReturn(jobOffer);
        return jobOffer;
    }

    private JobOffer stubRepositoryToReturnOneJobOffer(long jobOfferId) {
        final Skill skill = TestUtils.createSkill();
        final JobOffer jobOffer = TestUtils.creteJobOffer(skill);
        when(jobOffersRepository.findByIdJobOffer(jobOfferId)).thenReturn(jobOffer);
        return jobOffer;
    }

    private void stubRepositoryToReturnUser(String userLogin) {
        final UserAccount userAccount = TestUtils.creteUser();
        when(userAccountRepository.findByLogin(userLogin)).thenReturn(userAccount);
    }

    private void stubRepositoryToReturnUserWithExternalDocument(String userLogin) {
        final UserAccount userAccount = TestUtils.creteUserWithExternalDocument();
        when(userAccountRepository.findByLogin(userLogin)).thenReturn(userAccount);
    }
}
