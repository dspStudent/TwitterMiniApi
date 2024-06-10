package com.twitter.mini.security.jwt;

import com.twitter.mini.entites.Users;
import com.twitter.mini.globalExceptions.exceptionImp.UserTokenIsInvalidException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    JwtService jwtService;
    @Autowired
    UserDetailsService userDetailsService;
    @SneakyThrows
    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                     @NonNull HttpServletResponse response,
                                     @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");
            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            String token = header.substring(7);
            String userName = jwtService.getUserClaims(token);
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                // to-do add authorities when needed
//            if(!userDetails.isEnabled()){
//                throw new UserNotVerfiedException("user not verfied execption");
//            }
                Query query = new Query(Criteria.where("userName").is(userDetails.getUsername()).and("logOut").is(true));
                Users users = mongoTemplate.findOne(query, Users.class);
                if (users != null) throw new UserTokenIsInvalidException("user should login");
                if (jwtService.isTokenExpired(token)) {
                    throw new UserTokenIsInvalidException("user token expired create a new token using verificatiom");
                }
                if (!jwtService.isTokenValid(token, userDetails)) {
                    throw new UserTokenIsInvalidException("user token is invalid");
                }
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            filterChain.doFilter(request, response);
        }
        catch (Exception e){
            logger.info(e.getLocalizedMessage());
            response.sendRedirect("http://127.0.0.1:5500/index.html");
        }
    }

    //don't do filter
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/auth");
    }
}
