package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.UserRepository;
import com.dh.clinicadental.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements ApplicationRunner {

    @Autowired
    AppUserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) {
        //crear();
    }


    private void crear() {

        String hashedPassword3 = passwordEncoder.encode("pass");
        userRepository.save(new AppUser("Leandro", "locchiato", hashedPassword3));
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .authorizeRequests()
                   /* .antMatchers("/index.html").hasAnyRole("USER")
                    .antMatchers("/alta-odontologos.html").hasRole("ADMIN")
                    .antMatchers("/odontologos.html").hasRole("ADMIN")
                    .antMatchers("/alta-pacientes.html").hasRole("ADMIN")
                    .antMatchers("/pacientes.html").hasRole("ADMIN")*/
                    .anyRequest()
                    .permitAll()
                    .and()
                    .formLogin()
                    .and()
                    .logout();


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}


