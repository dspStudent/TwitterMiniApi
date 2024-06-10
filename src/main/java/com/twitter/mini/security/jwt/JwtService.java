package com.twitter.mini.security.jwt;

import com.twitter.mini.security.auth.AuthResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {
    private final String SECRET="9a2f8c4e6b0d71f3e8b925a45747f894a3d6bc70fa8d5e21a15a6d8c3b9a0e7c";
    private final Long tokenExpireTime= (long) (15*60*60*1000);
    private final Long refreshTokenExpireTime=(long) (5*60*60*1000);

    public String getUserClaims(String token) {
        Claims claims=getAllClaims(token);
        return claims.getSubject();
    }
    public Key getSigningKey(){
        byte[] key= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(key);
    }
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String genrateToken(UserDetails userDetails) {
        return genrateJwtToken(userDetails, tokenExpireTime);
    }

    private String genrateJwtToken(UserDetails userDetails, Long expireTime) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expireTime))
                .signWith(getSigningKey())
                .compact();
    }

    public String genrateRefreshToken(UserDetails userDetails) {
        return genrateJwtToken(userDetails, refreshTokenExpireTime);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        Claims claims=getAllClaims(token);
        return (claims.getSubject().equals(userDetails.getUsername()));
    }

    public boolean isTokenExpired(String token) {
        Claims claims=getAllClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }

    public AuthResponse newTokenGenrate(String token) {
        Claims claims=getAllClaims(token);
        return AuthResponse
                .builder()
                .token(
                        Jwts
                                .builder()
                                .setSubject(claims.getSubject())
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis()+tokenExpireTime))
                                .signWith(getSigningKey())
                                .compact()
                )
                .refreshToken(
                        Jwts
                                .builder()
                                .setSubject(claims.getSubject())
                                .setIssuedAt(new Date(System.currentTimeMillis()))
                                .setExpiration(new Date(System.currentTimeMillis()+refreshTokenExpireTime))
                                .signWith(getSigningKey())
                                .compact()
                )
                .build();
    }
}
