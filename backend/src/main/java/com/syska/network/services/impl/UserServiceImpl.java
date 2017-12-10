package com.syska.network.services.impl;

import com.google.common.collect.ImmutableList;
import com.syska.network.entities.Role;
import com.syska.network.entities.User;
import com.syska.network.repositories.UserRepository;
import com.syska.network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public void save(User user){
        User byUsername = userRepository.findByUsername(user.getUsername());
        if (byUsername == null){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            user.setAuthorities(ImmutableList.of(new Role("ROLE_USER")));
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setEnabled(true);
            user.setDateCreate(new Date());
            userRepository.save(user);
        }else {
            if(user.getPassword()!=null){
                user.setPassword(passwordEncoder().encode(user.getPassword()));
            }
            user.setAccountNonExpired(byUsername.isAccountNonExpired());
            user.setAccountNonLocked(byUsername.isAccountNonLocked());
            user.setCredentialsNonExpired(byUsername.isCredentialsNonExpired());
            user.setEnabled(byUsername.isEnabled());
            userRepository.save(user);
        }
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return ImmutableList.copyOf(userRepository.findAll());
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User findByPhone(String phone){
        return userRepository.findByPhone(phone);
    }
}
