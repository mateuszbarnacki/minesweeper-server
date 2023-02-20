package com.example.ztiproj.user.service;

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
public class UserService implements UserDetailsService {
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
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

    public void registerUser(AuthenticationDto authenticationDto) {
        User user = new User();
        user.setUsername(authenticationDto.username());
        user.setPassword(encoder.encode(authenticationDto.password()));
        userRepository.save(user);
    }
}
