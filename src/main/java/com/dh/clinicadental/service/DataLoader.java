package com.dh.clinicadental.service;

import com.dh.clinicadental.model.AppUser;
import com.dh.clinicadental.model.AppUserRole;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();

        String passHashed = passEncoder.encode("pass");
        String passHashed2 = passEncoder.encode("pass2");

        AppUser user1 =
                new AppUser("Agus","Viruel","agustina@gmail.com",passHashed, AppUserRole.ADMIN);
        AppUser user2 =
                new AppUser("Ani","Fernandez","anahi@gmail.com",passHashed2, AppUserRole.USER);

    }
}
