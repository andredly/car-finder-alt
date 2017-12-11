package com.syska.network.controllers;

import com.syska.network.entities.User;
import com.syska.network.pojos.UserRegistration;
import com.syska.network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private Converter<UserRegistration,User> userRegistrationConverter;

    @Autowired
    private Converter<User,UserRegistration> userConverter;

    @PostMapping(value = "/register",produces = "application/json")
    public ResponseEntity<UserRegistration> register(@RequestBody @Valid UserRegistration userRegistration){
        userService.save(userRegistrationConverter.convert(userRegistration));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole(\"ROLE_ADMIN\")")
    @GetMapping(value = "/all")
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }


    @GetMapping(value = "/logout")
    public void logout(@RequestParam(value = "access_token") String accessToken){
        tokenStore.removeAccessToken(tokenStore.readAccessToken(accessToken));
    }


    @PreAuthorize("#username == authentication.name or hasRole(\"ROLE_ADMIN\")")
    @GetMapping(value ="/user/{username}")
    public ResponseEntity<UserRegistration> getByUsername(
                                @PathVariable("username") String username){
        return new ResponseEntity<>(userConverter.convert(userService.findByUsername(username)), HttpStatus.OK);
    }

}
