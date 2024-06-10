package com.twitter.mini.controller.followController;

import com.twitter.mini.entites.Follow;
import com.twitter.mini.entites.Users;
import com.twitter.mini.repository.FollowRepository;
import com.twitter.mini.util.GetUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class GetFollowers {
    private static final Logger log = LoggerFactory.getLogger(GetFollowers.class);
    @Autowired
    GetUserName getUserName;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    MongoTemplate mongoTemplate;
    @GetMapping("/myFollowers")
    public List<Users> getFollowers(){
        String userName=getUserName.getUserNameFromToken();
//        List<Follow>follwers=followRepository.findAllByUser2(userName);
//        log.info(follwers.toString());
        List<Users> userList=mongoTemplate.findAll(Users.class);
        return userList.stream()
                        .filter(user1-> !user1.getFirstName().equals(userName) &&
                                followRepository.
                                        findByUser1AndUser2(user1.getUsername(), userName).
                                        isPresent()).
                toList();
    }
}
