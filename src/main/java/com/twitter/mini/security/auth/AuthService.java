package com.twitter.mini.security.auth;

import com.twitter.mini.entites.UserModal;
import com.twitter.mini.entites.Users;
import com.twitter.mini.globalExceptions.exceptionImp.UserCredentailsAreWrong;
import com.twitter.mini.globalExceptions.exceptionImp.UserNotFoundException;
import com.twitter.mini.repository.UserRepository;
import com.twitter.mini.security.jwt.JwtService;
import com.twitter.mini.util.ImagesUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;
    @Autowired
    ImagesUrl imagesUrl;
    public UserModal signUp(UserModal userModal) {
        char img=userModal.getFirstName().toLowerCase().charAt(0);
        String imgUrl1="";
        if(img < 'a' || img>'z')
            imgUrl1=imagesUrl.getDefaultImage();
        else
         imgUrl1=imagesUrl.getImageUrls().get(img-'a');
        log.info(imgUrl1);
        log.info("{}", img);
        Users users=Users
                .builder()
                .firstName(userModal.getFirstName())
                .imgUrl(imgUrl1)
                .lastName(userModal.getLastName())
                .userName(userModal.getUserName().toLowerCase())
                .email(userModal.getEmail())
                .password(passwordEncoder.encode(userModal.getPassword()))
                .role("User")
                .build();
        userRepository.save(users);
        return userModal;
    }

    public AuthResponse login(AuthRequest authRequest) throws UserNotFoundException, UserCredentailsAreWrong {
        UserDetails userDetails=null;
        try{
            userDetails= userDetailsService.loadUserByUsername(authRequest.getUserName().toLowerCase());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName().toLowerCase(), authRequest.getPassword(), userDetails.getAuthorities());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (Exception exception){
            throw new UserCredentailsAreWrong("users credintals are wrong");
        }
        Query query=new Query(Criteria.where("userName").is(userDetails.getUsername()));
        Update update=new Update().set("logOut", false);
        mongoTemplate.updateFirst(query, update, Users.class);
        return AuthResponse
                .builder()
                .token(jwtService.genrateToken(userDetails))
                .refreshToken(jwtService.genrateRefreshToken(userDetails))
                .build();
    }
}
