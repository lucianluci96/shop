package org.myproject.shop.config;

import org.myproject.shop.rest.dto.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {


        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/")
                .antMatchers("/product/**")
                .antMatchers("/shop/**")
                .antMatchers("/manager/**")
                .antMatchers("/input/**")
                .antMatchers("/output/**")
                .antMatchers("/user/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic().and()
                .authorizeRequests()
//                .antMatchers("/product/**").permitAll()
//                .antMatchers("/shop/**").permitAll()
//             .antMatchers("/user/**").hasRole(RoleEnum.ADMIN.getRoleName())
//                .antMatchers("/shop/**").hasAuthority("ADMIN")
                .antMatchers("/stock/list").hasRole((RoleEnum.ADMIN.getRoleName()))


                .anyRequest()
                .fullyAuthenticated().and().httpBasic()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable()
        ;
    }
}




