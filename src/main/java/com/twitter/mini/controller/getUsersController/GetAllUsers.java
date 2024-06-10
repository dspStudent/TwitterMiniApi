package com.twitter.mini.controller.getUsersController;

import com.twitter.mini.entites.Users;
import com.twitter.mini.repository.FollowRepository;
import com.twitter.mini.util.GetUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class GetAllUsers {
    private static final Logger log = LoggerFactory.getLogger(GetAllUsers.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    FollowRepository followRepository;
    @Autowired
    GetUserName getUserName;
    @GetMapping("/allUsers")
    public ResponseEntity<List<Users>> getAllUsers(){
        String userName=getUserName.getUserNameFromToken();
        List<Users>users=mongoTemplate.findAll(Users.class);
        List<Users>reUsers=users.stream()
                .filter(user-> !userName.equals(user.getUsername()) &&
                        followRepository.
                                findByUser1AndUser2(userName, user.getUsername())
                                .isEmpty())
                .toList();
        log.info(reUsers.toString());
        return ResponseEntity.ok(reUsers);
    }
}
