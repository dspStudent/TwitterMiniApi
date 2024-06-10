package com.twitter.mini.controller;

import com.twitter.mini.entites.Tweet;
import com.twitter.mini.entites.Users;
import com.twitter.mini.repository.TweetRepository;
import com.twitter.mini.util.GetUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class PostTweets {
    private static final Logger log = LoggerFactory.getLogger(PostTweets.class);
    @Autowired
    TweetRepository tweetRepository;
    @Autowired
    GetUserName getUserName;
    @Autowired
    MongoTemplate mongoTemplate;
    @PostMapping("/postTweet")
    public ResponseEntity<Tweet> postTweet(@RequestParam(required = false) String body){
        String userName=getUserName.getUserNameFromToken();
        log.info(userName);
        Query query=new Query(Criteria.where("userName").is(userName));
        List<Users> users=mongoTemplate.find(query, Users.class);
        log.info(users.toString());
        log.info(users.getFirst().getImgUrl());
        log.info(users.get(0).getImgUrl());
        if(body==null)body+="";
        return ResponseEntity.ok(tweetRepository.save(Tweet.builder()
                .time(LocalDateTime.now())
                .user(userName)
                .imgUrl(users.getFirst().getImgUrl())
                .content(body)
                .build()));
    }
}
