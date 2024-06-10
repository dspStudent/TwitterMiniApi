package com.twitter.mini.repository;

import com.twitter.mini.entites.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, ObjectId> {

    Optional<UserDetails> findByUserName(String username);

    Optional<Users> findByUserNameIgnoreCase(String userName);
}
