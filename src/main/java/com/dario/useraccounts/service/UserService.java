package com.dario.useraccounts.service;

import com.dario.useraccounts.model.User;
import com.dario.useraccounts.model.JwtResponse;

import java.util.Optional;

public interface UserService {

    User getUser(Long userId);

    User getUserByName(String username);

    Optional<User> getUserByEmail(String email);

    User createUser(User user);

    JwtResponse updateUser(User user);
}
