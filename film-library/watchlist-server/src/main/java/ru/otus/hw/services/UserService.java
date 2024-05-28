package ru.otus.hw.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.otus.hw.models.User;

public interface UserService extends UserDetailsService {

    void create(User user);

}
