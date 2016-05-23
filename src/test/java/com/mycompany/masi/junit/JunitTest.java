package com.mycompany.masi.junit;

import com.mycompany.masi.exception.JobOfferNotFoundException;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.repository.JobOffersRepository;
import com.mycompany.masi.repository.UserAccountRepository;
import com.mycompany.masi.service.JobOffersService;
import com.mycompany.masi.service.UserService;
import java.util.List;
import java.util.UUID;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JunitTest {

    @Mock
    private JobOffersRepository jobOffersRepository;
    @Mock
     private UserAccountRepository userAccountRepository;

    private JobOffersService jobOffersService;
    private UserService userService;

    @Before
    public void setUp() {
        jobOffersService = new JobOffersService(jobOffersRepository);
        userService= new UserService(userAccountRepository);
    }

    @Test
    public void shouldAddJobOffer_TheAddedJobOfferShouldBeReturned() {
        final JobOffer jobOffer = TestUtils.creteJobOffer();
        final JobOffer addedJobOffer = stubRepositoryToReturnAddedJobOffer(jobOffer);
        final JobOffer returnedJobOffer = jobOffersService.addJobOffer(jobOffer);

        // verify repository was called with document
        verify(jobOffersRepository, times(1)).save(jobOffer);
        assertEquals("Returned job offer should be the same as added job ofer", addedJobOffer, returnedJobOffer);
    }

    @Test
    public void shouldGetJobOfferById_JobOfferShouldBeReturned() {
        final String jobOfferId = UUID.randomUUID().toString();
        final JobOffer receivedJobOffer = stubRepositoryToReturnOneJobOffer(jobOfferId);
        final JobOffer returnedJobOffer = jobOffersService.getJobOfferById(jobOfferId);

        // verify repository was called with document
        verify(jobOffersRepository, times(1)).findById(jobOfferId);
        assertEquals("Returned job offer should be the same as received job ofer", receivedJobOffer, returnedJobOffer);
    }

    @Test(expected = JobOfferNotFoundException.class)
    public void shouldGetJobOfferById_JobOfferNotFound_TheExceptionShouldBeThrown() {
        final String jobOfferId = UUID.randomUUID().toString();
        jobOffersService.getJobOfferById(jobOfferId);

        // verify repository was called with document
        verify(jobOffersRepository, times(1)).findById(jobOfferId);
    }
    
        @Test
    public void shouldGetUserExternalDocuments_ListShouldBeNull() {
        String userLogin="kowalski";
            stubRepositoryToReturnUser(userLogin);
        List<ExternalDocument> externalDocuments=userService.getAllUserExternalDocument(userLogin);

        // verify repository was called with document
        verify(userAccountRepository, times(1)).findByLogin(userLogin);
        assertThat("Returned external documents list should be null", externalDocuments, is(empty()));
    }

    private JobOffer stubRepositoryToReturnAddedJobOffer(JobOffer jobOffer) {
        //final JobOffer jobOffer=TestUtils.creteJobOffer();
        when(jobOffersRepository.save(jobOffer)).thenReturn(jobOffer);
        return jobOffer;
    }

    private JobOffer stubRepositoryToReturnOneJobOffer(String jobOfferId) {
        final JobOffer jobOffer = TestUtils.creteJobOffer();
        when(jobOffersRepository.findById(jobOfferId)).thenReturn(jobOffer);
        return jobOffer;
    }

        private void stubRepositoryToReturnUser(String userLogin) {
        final UserAccount userAccount=  TestUtils.creteUser();
        when(userAccountRepository.findByLogin(userLogin)).thenReturn(userAccount);
    }
}
