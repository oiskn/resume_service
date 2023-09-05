package com.example.resume_service.service.user;

import com.example.resume_service.exception.notfound.UserNotFoundException;
import com.example.resume_service.model.User;
import com.example.resume_service.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        return userRepository
                .findByLogin(login)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public void save(User user) {
        Optional<User> existedUser = userRepository.findByLogin(user.getLogin());
        existedUser.ifPresent(u -> user.setId(u.getId()));

        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean checkAuthUser() {
        return userRepository
                .existsByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
