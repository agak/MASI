package com.mycompany.masi.controller;

import com.mycompany.masi.model.CurriculumVitae;
import com.mycompany.masi.model.ExternalDocument;
import com.mycompany.masi.model.JobApplication;
import com.mycompany.masi.model.UserAccount;
import com.mycompany.masi.service.UserAccountService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;

@CrossOrigin
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserAccountService userService;

    @Inject
    public UserController(final UserAccountService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/addCv", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public CurriculumVitae addCv(@Validated @RequestBody(required = true) CurriculumVitae curriculumVitae, @RequestParam(value = "login", required = true) String login) {
        LOGGER.info("Start addCv  :: " + login);
        return userService.addCv(curriculumVitae, login);
    }

    @RequestMapping(value = "/getCv", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public CurriculumVitae getCvByUser(@RequestParam(value = "login", required = true) String login) {
        LOGGER.info("Start getCv  :: " + login);
        return userService.getCvByUser(login);
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public UserAccount editUser(@Validated @RequestBody(required = true) UserAccount userAccount) {
        LOGGER.info("Start editUser  :: ");
        return userService.editUser(userAccount);
    }

    @RequestMapping(value = "/uploadFile",
            method = RequestMethod.POST)
    public ExternalDocument uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Start UploadFile  :: ");
        Iterator<String> itr = request.getFileNames();
        MultipartFile file = request.getFile(itr.next());
        return userService.uploadFile(file);
    }
}
