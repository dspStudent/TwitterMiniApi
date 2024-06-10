package com.twitter.mini.controller.followController;

import com.twitter.mini.repository.FollowRepository;
import com.twitter.mini.util.GetUserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class IsIAmFollowing {
    @Autowired
    GetUserName getUserName;
    @Autowired
    FollowRepository followRepository;
    @GetMapping("/isIAmFollowing")
    public ResponseEntity<Boolean> isIAmFollowing(@RequestParam(required = true) String user2){
        String userName=getUserName.getUserNameFromToken();
        return ResponseEntity.ok(followRepository.findByUser1AndUser2(userName, user2).isPresent());
    }
}
