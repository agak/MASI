package com.mycompany.masi.service;

import com.mycompany.masi.exception.JobOfferNotFoundException;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.repository.JobOffersRepository;
import com.mycompany.masi.repository.SkillRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class JobOffersService {

    private final JobOffersRepository jobOffersRepository;
    private final SkillRepository skillRepository;

    @Inject
    public JobOffersService(final JobOffersRepository jobOffersRepository, final SkillRepository skillRepository) {
        this.jobOffersRepository = jobOffersRepository;
        this.skillRepository = skillRepository;
    }

    public JobOffer addJobOffer(JobOffer jobOffer) {
        return jobOffersRepository.save(jobOffer);
    }

    public JobOffer getJobOfferById(long jobOfferId) {
        JobOffer jobOffer = jobOffersRepository.findByIdJobOffer(jobOfferId);
        if (jobOffer == null) {
            throw new JobOfferNotFoundException(jobOfferId);
        }
        return jobOffer;
    }

    public List<JobOffer> getAllJobOffers() {
        return jobOffersRepository.findAll();
    }

}
