package com.dario.useraccounts.service;

import com.dario.useraccounts.exceptions.AttributeException;
import com.dario.useraccounts.model.User;
import com.dario.useraccounts.model.JwtResponse;
import com.dario.useraccounts.repository.UserRepository;
import org.springframework.stereotype.Service;

import com.dario.useraccounts.exceptions.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private static final Logger LOGGER = Logger.getLogger("com.dario.registeruser.service.UserService");
    private static final String USER_NOT_FOUND = "User not found!.";
    private static final String USER_UPDATE = "User succesfully updated.";
    private static final String EMAIL_ALREADY_USED = "User with email already exists: ";
    private static final String USER_CREATE = "User succesfully created.";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            LOGGER.log(Level.WARNING, USER_NOT_FOUND);
            return new EntityNotFoundException(User.class);
        });
    }

    @Override
    public User getUserByName(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> {
            LOGGER.log(Level.WARNING, USER_NOT_FOUND);
            return new EntityNotFoundException(User.class, "id", username.toString());
        });
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        String email = user.getEmail();
        if(getUserByEmail(email).isPresent()){
            LOGGER.log(Level.WARNING, () -> EMAIL_ALREADY_USED + email);
            throw new AttributeException(EMAIL_ALREADY_USED);
        }
        String uuid = UUID.randomUUID().toString();
        Date created = new Date(System.currentTimeMillis());
        user.setCreated(created);
        user.setModified(created);
        user.setLastLogin(created);
        user.setToken(uuid);
        user.setIsActive(true);
        userRepository.save(user);
        LOGGER.log(Level.INFO, USER_CREATE);
        return user;
    }

    @Override
    public JwtResponse updateUser(User user) {
        userRepository.findById(user.getUserid()).orElseThrow(() -> {
            LOGGER.log(Level.WARNING, USER_NOT_FOUND);
            return new EntityNotFoundException(User.class);
        });
        userRepository.save(user);
        LOGGER.log(Level.INFO, USER_UPDATE);
        return null;
    }
}
