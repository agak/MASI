package com.mycompany.masi.controller;

import com.mycompany.masi.model.JobOffer;
import com.mycompany.masi.service.JobOffersService;
import java.util.List;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(value = "/jobOffers", produces = MediaType.APPLICATION_JSON_VALUE)
public class JobOffersController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobOffersController.class);

    private final JobOffersService jobOffersService;

    @Inject
    public JobOffersController(final JobOffersService jobOffersService) {
        this.jobOffersService = jobOffersService;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List <JobOffer> getAllJobOffers() {
        LOGGER.info("Start getAllJobOffers :: ");
        return jobOffersService.getAllJobOffers();
    }
    
        @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public JobOffer getJobOfferById(@RequestParam(value = "jobOfferId", required = true)String jobOfferId ) {
        LOGGER.info("Start getJobOfferById :: ");
         return jobOffersService.getJobOfferById(jobOfferId);

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public JobOffer addJobOffer(@Validated @RequestBody(required = true) JobOffer jobOffer) {
        LOGGER.info("Start addJobOffer  :: ");
        return jobOffersService.addJobOffer(jobOffer);

    }
}
