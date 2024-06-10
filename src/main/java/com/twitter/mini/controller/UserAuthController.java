package com.twitter.mini.controller;

import com.twitter.mini.entites.UserModal;
import com.twitter.mini.globalExceptions.exceptionImp.EmptyContentGetException;
import com.twitter.mini.globalExceptions.exceptionImp.UserCredentailsAreWrong;
import com.twitter.mini.globalExceptions.exceptionImp.UserIsAlredyExistedException;
import com.twitter.mini.globalExceptions.exceptionImp.UserNotFoundException;
import com.twitter.mini.repository.UserRepository;
import com.twitter.mini.security.auth.AuthRequest;
import com.twitter.mini.security.auth.AuthResponse;
import com.twitter.mini.security.auth.AuthService;
import com.twitter.mini.security.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/userExist")
    public ResponseEntity<Boolean> userExist(@RequestParam (required = true) String name){
        log.info(userRepository.findByUserName(name).toString());
        return ResponseEntity.ok(userRepository.findByUserNameIgnoreCase(name).isPresent());
    }
    @PostMapping("/signUp")
    public ResponseEntity<UserModal> signUp(@RequestBody UserModal userModal) throws UserIsAlredyExistedException, EmptyContentGetException {
        if(userModal==null)throw new EmptyContentGetException("user data is empty");
        if(userRepository.findByUserNameIgnoreCase(userModal.getUserName()).isPresent())
            throw new UserIsAlredyExistedException("user alredy exited");
        log.info(userModal.toString());
        return ResponseEntity.ok(authService.signUp(userModal));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) throws UserNotFoundException, UserCredentailsAreWrong {
        log.info(authRequest.toString());
        return ResponseEntity.ok(authService.login(authRequest));
    }
    @GetMapping("/getRefreshToken")
    public ResponseEntity<AuthResponse> getRefreshToken(@RequestParam(required = true) String token){
        return ResponseEntity.ok(jwtService.newTokenGenrate(token));
    }

}
