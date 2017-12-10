package com.syska.network.services;

import com.syska.network.entities.User;

import java.util.List;

public interface UserService {

	void save(User user);

	User findByUsername(String username);

	List<User> findAll();

	User findByEmail(String email);

	User findByPhone(String phone);
}
