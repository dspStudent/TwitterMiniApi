package com.twitter.mini.controller.getUsersController;

import com.twitter.mini.entites.Follow;
import com.twitter.mini.entites.Tweet;
import com.twitter.mini.repository.FollowRepository;
import com.twitter.mini.repository.TweetRepository;
import com.twitter.mini.util.GetUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class GetTweetsOfFollowed {
    private static final Logger log = LoggerFactory.getLogger(GetTweetsOfFollowed.class);
    @Autowired
    GetUserName getUserName;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    TweetRepository tweetRepository;
    @GetMapping("/home")
    public ResponseEntity<List<Tweet>> getTweets(){
        String userName=getUserName.getUserNameFromToken();
        List<Tweet> allTweets=new ArrayList<>();
        List<Follow> followList = followRepository.findAllByUser1(userName);
        if (followList == null || followList.isEmpty()) return ResponseEntity.ok(null);
        List<String> users = followList.stream().map(Follow::getUser2).toList();
        log.info(users.toString());
        allTweets = users.stream()
                .flatMap(user -> tweetRepository.findAllByUser(user).stream())
                .collect(Collectors.toList());
        Collections.sort(allTweets, (i,j)->j.getTime().compareTo(i.getTime()));
        log.info(allTweets.toString());
        return ResponseEntity.ok(allTweets);
    }
}
