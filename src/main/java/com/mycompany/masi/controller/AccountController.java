package com.mycompany.masi.controller;

import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.service.AccountService;
import com.mycompany.masi.service.UserService;
import java.security.Principal;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobOffersController.class);

    private final AccountService accountService;

    @Inject
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/login")
  //  @ResponseStatus(HttpStatus.OK)
    public Principal  login( Principal account) {
        LOGGER.info("Start login  :: "+account);
      return  account;

    }
}
