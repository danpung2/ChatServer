package com.example.chatprojectspringboot.common.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.chatprojectspringboot.common.security.service.PrincipalDetails;
import com.example.chatprojectspringboot.entity.User;
import com.example.chatprojectspringboot.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthorizationFilter  extends BasicAuthenticationFilter {

    @Value("${jwt.access_secret}")
    private String ACCESS_SECRET;

    @Value("${jwt.access_prefix}")
    private String ACCESS_PREFIX;

    @Value("${jwt.access-header}")
    private String ACCESS_HEADER;

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String header = request.getHeader("${jwt.access_secret}");

        if (header == null || !header.startsWith(ACCESS_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        String token = request.getHeader(ACCESS_HEADER).replace(ACCESS_PREFIX, "");

        try {
            int userId = Integer.parseInt(String.valueOf(JWT.require(Algorithm.HMAC512(ACCESS_SECRET)).build().verify(token).getClaim("userId")));

            log.info("JwtAuthorizationFilter userId : " + userId);
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NullPointerException("NOT FOUND MANAGER id: " + userId));

            PrincipalDetails principalDetails = new PrincipalDetails(user);
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    principalDetails,
                    null,
                    principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            log.info("Access Token Expired : " + e.getLocalizedMessage());
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}