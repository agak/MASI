package com.mycompany.masi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JobOfferNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobOfferNotFoundException.class);

    public JobOfferNotFoundException(long jobOfferId) {
        super("Could not find job offer '" + jobOfferId + "'.");
        LOGGER.error("Could not find job offer '" + jobOfferId + "'.");
    }
}