package com.mycompany.masi.controller;

import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.service.UserService;
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
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobOffersController.class);

    private final UserService userService;

    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/addCv", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public CurriculumVitae addCv(@Validated @RequestBody(required = true) CurriculumVitae curriculumVitae) {
        LOGGER.info("Start jobApply  :: ");
        return userService.addCv(curriculumVitae);

    }
}
