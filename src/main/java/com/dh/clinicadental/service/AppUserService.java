package com.dh.clinicadental.service;

import com.dh.clinicadental.GlobalExceptionHandler;
import com.dh.clinicadental.dao.UserRepository;
import com.dh.clinicadental.model.AppUser;
import com.dh.clinicadental.model.Rol;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(AppUserService.class);

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            logger.info("Cargando usuario...");
            Optional<AppUser> user = userRepository.getUserByName(userName);

            Set<GrantedAuthority> autorizaciones = new HashSet<>();
            for(Rol rol: user.get().getAppUserRole())
            {
                GrantedAuthority autorizacion = new SimpleGrantedAuthority(rol.getName());
                autorizaciones.add(autorizacion);

            }

            User userDetail = new User(user.get().getName(),"{noop}" + user.get().getPassword(),true,
                    true, true,true,autorizaciones);
            return userDetail;
        }

}
