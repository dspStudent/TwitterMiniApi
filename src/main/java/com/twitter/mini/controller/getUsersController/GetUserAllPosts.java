package com.twitter.mini.controller.getUsersController;

import com.twitter.mini.entites.Tweet;
import com.twitter.mini.repository.TweetRepository;
import com.twitter.mini.util.GetUserName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tweet")
public class GetUserAllPosts {
    @Autowired
    GetUserName getUserName;
    @Autowired
    TweetRepository tweetRepository;
    @GetMapping("/myPosts")
    public ResponseEntity<List<Tweet>> getMyPosts(){
        String userName= getUserName.getUserNameFromToken();
        List<Tweet>tweets=tweetRepository.findAllByUser(userName);
        tweets.sort((i, j) -> j.getTime().compareTo(i.getTime()));
        return ResponseEntity.ok(tweets);
    }
    @DeleteMapping("/deleteMyPost")
    public ResponseEntity<String> deleteMyPost(@RequestBody Tweet tweet){
     Optional<Tweet> tweetOp=tweetRepository.findAllByUserAndTimeAndContent(
                tweet.getUser(), tweet.getTime(), tweet.getContent()
        );
        log.info(tweetOp.toString());
        if(tweetOp.isEmpty())return ResponseEntity.badRequest().body("the tweet you want to delete is not there");
        tweetRepository.delete(tweetOp.get());
        log.info("after delete");
        return ResponseEntity.ok("deleted");
    }
    @PutMapping("/editTweet")
    public ResponseEntity<String> editTweet(@RequestBody Tweet tweetEdit, @RequestParam String body) {
        Optional<Tweet> tweet=tweetRepository.findAllByUserAndTimeAndContent(
                tweetEdit.getUser(), tweetEdit.getTime(), tweetEdit.getContent()
        );
        log.info(tweet.toString());
        if(tweet.isEmpty())return ResponseEntity.badRequest().body("the tweet you want to edit is not there");
        tweet.get().setContent(body);
        tweet.get().setTime(LocalDateTime.now());
        tweetRepository.save(tweet.get());
        return ResponseEntity.ok("tweet edited sucessfully");
    }

}
