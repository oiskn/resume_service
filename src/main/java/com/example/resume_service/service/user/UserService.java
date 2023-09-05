package com.example.resume_service.service.user;

import com.example.resume_service.model.User;

public interface UserService {
    User findByLogin(String login);

    void save(User user);

    boolean checkAuthUser();
}
