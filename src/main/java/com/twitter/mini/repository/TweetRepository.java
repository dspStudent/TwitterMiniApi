package com.twitter.mini.repository;

import com.twitter.mini.entites.Tweet;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TweetRepository extends MongoRepository<Tweet, ObjectId> {
    List<Tweet> findAllByUser(String user);

    Optional<Tweet> findAllByUserAndTimeAndContent(String user, LocalDateTime time, String content);
}
