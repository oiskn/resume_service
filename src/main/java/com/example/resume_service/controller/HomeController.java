package com.example.resume_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.example.resume_service.controller.util.PageNameUtils.*;
import static com.example.resume_service.controller.util.UniformResourceLocatorUtils.*;

@Controller
public class HomeController {

    @RequestMapping(value = LOGIN, method = RequestMethod.GET)
    public String loginPage() {
        return LOGIN_PAGE;
    }

    @RequestMapping(value = ADMIN, method = RequestMethod.GET)
    public String adminPage() {
        return ADMIN_PAGE;
    }

    @RequestMapping(value = USER, method = RequestMethod.GET)
    public String userPage() {
        return USER_PAGE;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String mainPage() {
        return MAIN_PAGE;
    }

    @RequestMapping(value = REGISTRATION, method = RequestMethod.GET)
    public String registrationPage() {
        return REGISTRATION_PAGE;
    }

    @RequestMapping(value = ALL_RESUME, method = RequestMethod.GET)
    public String allPage() {
        return ALL_RESUME_PAGE;
    }

    @RequestMapping(value = ADD_RESUME, method = RequestMethod.GET)
    public String addResumePage() {
        return ADD_RESUME_PAGE;
    }

    @RequestMapping(value = VIEW, method = RequestMethod.GET)
    public String viewPage() {
        return VIEW_PAGE;
    }
}
