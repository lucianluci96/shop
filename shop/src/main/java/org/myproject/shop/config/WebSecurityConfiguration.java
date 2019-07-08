package org.myproject.shop.config;

import org.myproject.shop.core.model.UserEntity;
import org.myproject.shop.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return username -> {
            UserEntity userEntity = userRepository.findByUsername(username);
            if (userEntity != null) {
                return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList(userEntity.getRole().getRoleName()));
            } else {
                throw new UsernameNotFoundException("could not find the user '" + username + "'");
            }
        };
    }
}