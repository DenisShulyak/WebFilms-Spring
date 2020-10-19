package com.example.service;


import com.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {//UserDetailsService, используется чтобы создать
    // UserDetails объект путем реализации единственного метода этого интерфейса

    @Autowired
    private  UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername( String s ) throws UsernameNotFoundException {
        return userRepo.findByUsername(s);
    }
}
