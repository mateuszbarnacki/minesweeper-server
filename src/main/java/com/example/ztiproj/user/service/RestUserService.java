package com.example.ztiproj.user.service;

import com.example.ztiproj.config.exception.ExistentUserException;
import com.example.ztiproj.user.dto.AuthenticationDto;
import com.example.ztiproj.user.model.User;
import com.example.ztiproj.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-16
 */
@Service
public class RestUserService implements UserDetailsService, UserService {
    private static final Logger LOGGER = Logger.getLogger(RestUserService.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public RestUserService(UserRepository repository, PasswordEncoder encoder) {
        this.userRepository = repository;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.log(Level.WARNING, "Could not find user with username: {0}", username);
            throw new UsernameNotFoundException("Given user does not exists!");
        }
        return user;
    }

    @Override
    public void registerUser(AuthenticationDto authenticationDto) {
        String username = authenticationDto.username();
        User user = userRepository.findUserByUsername(username);
        if (user != null) {
            logExistentUserException(username);
        }
        createNewUser(authenticationDto);
    }

    private void createNewUser(AuthenticationDto authenticationDto) {
        User newUser = new User();
        newUser.setUsername(authenticationDto.username());
        newUser.setPassword(encoder.encode(authenticationDto.password()));
        userRepository.save(newUser);
    }

    private void logExistentUserException(String username) {
        LOGGER.log(Level.WARNING, "User with given username already exists!");
        throw new ExistentUserException(username);
    }
}
