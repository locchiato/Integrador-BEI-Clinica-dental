package com.dh.clinicadental.service;

import com.dh.clinicadental.dao.UserRepository;
import com.dh.clinicadental.model.AppUser;
import com.dh.clinicadental.model.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<AppUser> appUser = userRepository.getUserByName(userName);

        Set<GrantedAuthority> autorizaciones = new HashSet<>();
        for(Rol rol: appUser.get().getAppUserRole())
        {
            GrantedAuthority autorizacion = new SimpleGrantedAuthority(rol.getName());
            autorizaciones.add(autorizacion);

        }

        return new User(appUser.get().getName(),"{noop}" + appUser.get().getPassword(),true, true, true,true,autorizaciones);
    }
}
