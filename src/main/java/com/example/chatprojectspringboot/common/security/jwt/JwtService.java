package com.example.chatprojectspringboot.common.security.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
public class JwtService {

    @Value("${jwt.access-secret}")
    private String ACCESS_SECRET;

    @Value("${jwt.access-prefix}")
    private String ACCESS_PREFIX;

    @Value("${jwt.access-header}")
    private String ACCESS_HEADER;

    @Value("${jwt.access-token-validity-in-seconds}")
    private int ACCESS_VALIDITY_IN_SECONDS;

    @Value("${jwt.refresh-secret}")
    private String REFRESH_SECRET;

    @Value("${jwt.refresh-prefix}")
    private String REFRESH_PREFIX;

    @Value("${jwt.refresh-header}")
    private String REFRESH_HEADER;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private int REFRESH_VALIDITY_IN_SECONDS;

    public JwtToken createToken(int userId) {
        return new JwtToken(
                JWT.create() // Access Token
                        .withSubject(String.valueOf(userId))
                        .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_VALIDITY_IN_SECONDS))
                        .withClaim("userId", userId)// getUsername() == getEmail()
                        .sign(Algorithm.HMAC512(ACCESS_SECRET)),
                JWT.create() // Refresh Token
                        .withSubject(String.valueOf(userId))
                        .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_VALIDITY_IN_SECONDS))
                        .withClaim("userId", userId)// getUsername() == getEmail()
                        .sign(Algorithm.HMAC512(REFRESH_SECRET))
        );
    }

    public String getAccessToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(ACCESS_HEADER).replace(ACCESS_PREFIX, "");
    }

    public String getRefreshToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        return request.getHeader(REFRESH_HEADER).replace(REFRESH_PREFIX, "");
    }

    public Integer getUserIdByAccessToken() throws JwtException {
        String accessToken = getAccessToken();
        if (accessToken == null || accessToken.length() == 0){
            throw new JwtException("TOKEN ERROR");
        }

        return Integer.parseInt(String.valueOf(JWT.require(Algorithm.HMAC512(ACCESS_SECRET)).build().verify(accessToken).getClaim("userId")));
    }

    public Integer getUserIdByRefreshToken() throws JwtException {
        String refreshToken = getRefreshToken();
        if (refreshToken == null || refreshToken.length() == 0){
            throw new JwtException("TOKEN ERROR");
        }

        return Integer.parseInt(String.valueOf(JWT.require(Algorithm.HMAC512(REFRESH_SECRET)).build().verify(refreshToken).getClaim("userId")));
    }
}

