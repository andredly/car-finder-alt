package com.syska.network.converters;

import com.syska.network.entities.User;
import com.syska.network.pojos.UserRegistration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationToUserConverter implements Converter <UserRegistration, User> {

    @Override
    public User convert(UserRegistration userRegistration) {
        return User.builder()
                .username(userRegistration.getUsername())
                .password(userRegistration.getPassword())
                .email(userRegistration.getEmail())
                .phone(userRegistration.getPhone())
                .build();
    }
}
