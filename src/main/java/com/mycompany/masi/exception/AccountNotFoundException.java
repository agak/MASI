package com.mycompany.masi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountNotFoundException.class);

    public AccountNotFoundException(String accountName) {
        super("Could not find account '" + accountName + "'.");
        LOGGER.error("Could not find account '" + accountName + "'.");
    }
}