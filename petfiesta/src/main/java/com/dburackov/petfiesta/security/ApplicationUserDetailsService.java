package com.dburackov.petfiesta.security;

import com.dburackov.petfiesta.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;



import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String id) {
        var user = userRepository.findById(Long.parseLong(id));

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("No such user with id " + id);
        }

        return new User(
                user.get().getId().toString(),
                user.get().getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("user"))
        );
    }
}
