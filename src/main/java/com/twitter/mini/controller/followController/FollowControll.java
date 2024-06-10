package com.twitter.mini.controller.followController;

import com.twitter.mini.entites.Follow;
import com.twitter.mini.globalExceptions.exceptionImp.UserNotFoundException;
import com.twitter.mini.repository.FollowRepository;
import com.twitter.mini.util.GetUserName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class FollowControll {
    private static final Logger log = LoggerFactory.getLogger(FollowControll.class);
    @Autowired
    private GetUserName getUserName;
    @Autowired
    FollowRepository followRepository;
    @PostMapping("/followOrUnfollow")
    public ResponseEntity<String> followOrUnfollowUser(@RequestParam(required = true)
                                                           String user2) throws UserNotFoundException {
        String user1=getUserName.getUserNameFromToken();
        if(user1==null)throw new UserNotFoundException("user not found");
        Optional<Follow> follow=followRepository.findByUser1AndUser2(user1, user2);
        boolean followIsThere=follow.isPresent();
        follow.ifPresent(follow1 -> {
            followRepository.delete(follow1);
        });
        log.info(follow.toString());
        if(followIsThere)return ResponseEntity.ok("un follwed");
        followRepository.save(Follow.builder().user1(user1).user2(user2).build());
        return ResponseEntity.ok("Followed");
    }
}
