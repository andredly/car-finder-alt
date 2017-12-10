package com.syska.network.repositories;

import com.syska.network.entities.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends MongoRepository<User, Long> {
//public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByPhone(String phone);
}
