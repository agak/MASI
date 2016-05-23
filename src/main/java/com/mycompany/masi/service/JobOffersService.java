package com.mycompany.masi.service;

import com.mycompany.masi.exception.JobOfferNotFoundException;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.repository.JobOffersRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class JobOffersService {

    private final JobOffersRepository jobOffersRepository;

    @Inject
    public JobOffersService(final JobOffersRepository jobOffersRepository) {
        this.jobOffersRepository = jobOffersRepository;
    }

    public JobOffer addJobOffer(JobOffer jobOffer) {
        return jobOffersRepository.save(jobOffer);
    }

    public JobOffer getJobOfferById(String jobOfferId) {
        JobOffer jobOffer = jobOffersRepository.findById(jobOfferId);
        if (jobOffer == null) {
            throw new JobOfferNotFoundException(jobOfferId);
        }
        return jobOffer;
    }

    public List<JobOffer> getAllJobOffers() {
        return jobOffersRepository.findAll();
    }

}
