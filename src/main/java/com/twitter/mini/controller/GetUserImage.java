package com.twitter.mini.controller;
import com.twitter.mini.entites.Users;
import com.twitter.mini.util.GetUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class GetUserImage {
    private static final Logger log = LoggerFactory.getLogger(GetUserImage.class);
    @Autowired
    GetUserName getUserName;
    @Autowired
    MongoTemplate mongoTemplate;
    @GetMapping("/userImage")
    public ResponseEntity<String> getUserImage(){
        String userName=getUserName.getUserNameFromToken();
        Query query=new Query(Criteria.where("useName").is(userName));
        List<Users>users=mongoTemplate.find(query, Users.class);
        String url=users.getFirst().getImgUrl();
        log.info(url);
        return ResponseEntity.ok(url);
    }
}
