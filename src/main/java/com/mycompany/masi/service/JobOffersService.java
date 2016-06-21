package com.mycompany.masi.service;

import com.mycompany.masi.exception.JobOfferNotFoundException;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.model.Skill;
import com.mycompany.masi.repository.JobApplicationRepository;
import com.mycompany.masi.repository.JobOffersRepository;
import com.mycompany.masi.repository.SkillRepository;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class JobOffersService {

    private final JobOffersRepository jobOffersRepository;
    private final SkillRepository skillRepository;
    private final JobApplicationRepository jobApplicationRepository;

    @Inject
    public JobOffersService(final JobOffersRepository jobOffersRepository, final SkillRepository skillRepository, final JobApplicationRepository jobApplicationRepository) {
        this.jobOffersRepository = jobOffersRepository;
        this.skillRepository = skillRepository;
        this.jobApplicationRepository = jobApplicationRepository;
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

    public JobApplication jobApply(long userId, long jobOfferId, String userMessage) {
        JobApplication jobApplication = new JobApplication(jobOfferId, userId, userMessage);
        return jobApplicationRepository.save(jobApplication);
    }

    @PostConstruct
    public void addSkills() {

        Skill skill1 = new Skill(null, "JAVA", "PROGRAMOWANIE", 10, null, null);
        Skill skill2 = new Skill(null, "C++", "PROGRAMOWANIE", 30, null, null);
        Skill skill3 = new Skill(null, "praca w grupie", "MIĘKKIE", 20, null, null);
        Skill skill4 = new Skill(null, "zarzadzanie projektami", "MIĘKKIE", 20, null, null);
        Skill skill5 = new Skill(null, "angularJS", "PROGRAMOWANIE", 30, null, null);
        Skill skill6 = new Skill(null, "NETBEANS", "PROGRAMOWANIE", 20, null, null);
        skillRepository.save(skill1);
        skillRepository.save(skill2);
        skillRepository.save(skill3);
        skillRepository.save(skill4);
        skillRepository.save(skill5);
        skillRepository.save(skill6);

    }

}
