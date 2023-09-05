package com.example.resume_service.config;

import com.example.resume_service.config.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Autowired
    public SecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService,
                             LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/registration").not().authenticated()
                .antMatchers("/all_resume").permitAll()
                .antMatchers("/image").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/image/save").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/image/delete").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/image/{id}").permitAll()
                .antMatchers("/resume/delete/{id}").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/resume/delete").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/resume").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/resume/myResume").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/resume/allPublishedResume").permitAll()
                .antMatchers("/resume").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/addResume").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/view").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/users/get").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
                .antMatchers("/users/save").permitAll()
                .antMatchers("/users/chek").permitAll()
                .anyRequest().anonymous()
                .and()
                .formLogin()
                .successHandler(new LoginSuccessHandler())
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .permitAll();
    }
}
