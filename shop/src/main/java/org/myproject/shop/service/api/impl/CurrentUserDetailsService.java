package org.myproject.shop.service.api.impl;

import org.myproject.shop.core.model.CurrentUser;
import org.myproject.shop.core.model.UserEntity;
import org.myproject.shop.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        return new CurrentUser(user);
    }
}

