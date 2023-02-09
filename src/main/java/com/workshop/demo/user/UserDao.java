package com.workshop.demo.user;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Repository
public class UserDao {
        private final static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
                        new User(
                                        "bouali.social@gmail.com",
                                        encoder.encode("password"),
                                        // "password",
                                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
                        new User(
                                        "user.mail@gmail.com",
                                        "password",
                                        Collections.singleton(new SimpleGrantedAuthority("ROLE: USER"))));

        public UserDetails findUserByEmail(String email) {
                return APPLICATION_USERS
                                .stream()
                                .filter(u -> u.getUsername().equals(email))
                                .findFirst()
                                .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
        }
}