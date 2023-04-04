package com.university.security;

import com.university.model.Users;
import com.university.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users userByEmail = userService.findUserByEmail(username);

        if (userByEmail == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }

        return User.builder()
                .username(userByEmail.getEmail())
                .password(userByEmail.getPassword())
                .roles(userByEmail.getRoles().toString())
                .build();
    }
}
