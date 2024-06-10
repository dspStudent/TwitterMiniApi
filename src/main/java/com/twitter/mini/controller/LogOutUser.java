package com.twitter.mini.controller;

import com.twitter.mini.entites.Users;
import com.twitter.mini.util.GetUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class LogOutUser {
    @Autowired
    GetUserName getUserName;
    @Autowired
    MongoTemplate mongoTemplate;
    @PutMapping("/logOut")
    public ResponseEntity<String> userLogOut() {
        String userName=getUserName.getUserNameFromToken();
        Query query=new Query(Criteria.where("userName").is(userName));
        Update update=new Update().set("logOut", true);
        mongoTemplate.updateFirst(query, update, Users.class);
        return ResponseEntity.ok( "user loged Out");
    }
}
