package com.twitter.mini.util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetUserName {
    public String getUserNameFromToken(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Object ob=authentication.getPrincipal();
        String user="";
        if(ob instanceof UserDetails){
            log.info(((UserDetails) ob).getUsername());
            user=((UserDetails) ob).getUsername();
        }
        return user;
    }
}
