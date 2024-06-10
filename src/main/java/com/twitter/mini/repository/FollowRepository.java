package com.twitter.mini.repository;

import com.twitter.mini.entites.Follow;
import com.twitter.mini.entites.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends MongoRepository<Follow, ObjectId> {
    Optional<Follow> findByUser1AndUser2(String user1, String user2);

    List<Follow> findByUser1(String userName);


    List<Follow> findAllByUser1(String userName);


    List<Follow> findAllByUser2(String userName);
}
