package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.UserRepository;
import com.dh.clinicadental.model.AppUser;
import com.dh.clinicadental.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoder2 = new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");

        Rol rol1 = new Rol();
        rol1.setName("ADMIN");
        Rol rol2 = new Rol();
        rol1.setName("USER");

        userRepository.save(new AppUser("Diego", "diego", "diego@digital.com", hashedPassword, Set.of(rol2)));
        userRepository.save(new AppUser("Paula", "paula", "paula@digital.com", hashedPassword2, Set.of(rol1)));
    }
}
