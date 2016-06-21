package com.mycompany.masi.controller;

import com.mycompany.masi.exception.AccountNotFoundException;
import com.mycompany.masi.model.Account;
import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.service.AccountService;
import com.mycompany.masi.service.AdminAccountService;
import com.mycompany.masi.service.CompanyAccountService;
import com.mycompany.masi.service.UserAccountService;
import java.security.Principal;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    //private final AccountService accountService;
    private final UserAccountService userService;
    private final CompanyAccountService companyAccountService;
    private final AdminAccountService adminAccountService;

    @Inject
    public AccountController(final UserAccountService userService, final CompanyAccountService companyAccountService, final AdminAccountService adminAccountService) {
      //  this.accountService = accountService;
        this.userService = userService;
        this.companyAccountService = companyAccountService;
        this.adminAccountService = adminAccountService;
    }

    @RequestMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public Principal login(Principal account) {
        LOGGER.info("Start login  :: " + account.getName());
        return account;

    }

    @RequestMapping(value = "/getLogUser" , method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Account getLogUser(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "role", required = true) String role) {
        LOGGER.info("Start getLogUser  :: " + login);
        Account account = null;

        if (role.equals("USER_ROLE")) {
            account = userService.getLogUser(login);
        } else if (role.equals("COMPANY_ROLE")) {
            account = companyAccountService.getLogUser(login);
        } else if (role.equals("ADMIN_ROLE")) {
            account = adminAccountService.getLogUser(login);
        } else {
            throw new AccountNotFoundException(login);
        }
        return account;

    }
}
