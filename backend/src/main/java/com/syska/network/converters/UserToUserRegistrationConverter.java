package com.syska.network.converters;

import com.syska.network.entities.User;
import com.syska.network.pojos.UserRegistration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserRegistrationConverter implements Converter <User, UserRegistration> {

    @Override
    public UserRegistration convert(User user) {
        return UserRegistration.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .phone(user.getPhone())
                .dateCreate(user.getDateCreate())
                .build();
    }
}
