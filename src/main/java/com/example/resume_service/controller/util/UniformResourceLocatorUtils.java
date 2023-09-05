package com.example.resume_service.controller.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UniformResourceLocatorUtils {

    public static final String LOGIN = "/login";

    public static final String ADMIN = "/admin";

    public static final String USER = "/user";

    public static final String REGISTRATION = "/registration";

    public static final String ALL_RESUME = "/all_resume";

    public static final String ADD_RESUME = "/addResume";

    public static final String VIEW = "/view";

    public static final String IMAGE = "/image";

    public static final String SAVE_IMAGE = IMAGE + "/save";

    public static final String FIND_IMAGE = IMAGE + "/{id}";

    public static final String DELETE_IMAGE = IMAGE + "/delete";

    public static final String RESUME = "/resume";

    public static final String SAVE_RESUME = RESUME + "/save";

    public static final String DELETE_USERS_RESUME = RESUME + "/delete";

    public static final String DELETE_RESUME_BY_ID = RESUME + "/delete/{id}";

    public static final String MY_RESUME = RESUME + "/myResume";

    public static final String ALL_PUBLISHED_RESUME = RESUME + "/allPublishedResume";

    public static final String ALL_SAVED_RESUME = RESUME + "/allResume";

    public static final String SAVE_USER = USER + "/save";

    public static final String GET_USER = USER + "/get";

    public static final String CHECK_AUTH_USER = USER + "/check";
}
