package com.example.chatprojectspringboot.controller;

import com.example.chatprojectspringboot.common.security.jwt.JwtService;
import com.example.chatprojectspringboot.common.security.jwt.JwtToken;
import com.example.chatprojectspringboot.dto.user.LoginDTO;
import com.example.chatprojectspringboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    @Value("${jwt.access-prefix}")
    private String ACCESS_PREFIX;

    @Value("${jwt.access-header}")
    private String ACCESS_HEADER;

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Validated LoginDTO loginDTO){
//
//
//    }

    @PostMapping("/login/success")
    public ResponseEntity<?> loginSuccess(HttpServletRequest request, HttpServletResponse response){
        int userId = (int) request.getAttribute("userId");
        JwtToken jwtToken = jwtService.createToken(userId);

        response.addHeader(ACCESS_HEADER, ACCESS_PREFIX+ jwtToken.getAccessToken());

        return ResponseEntity.ok(userService.getUserById(userId).setPassword(""));
    }
    @PostMapping("/login/fail")
    public ResponseEntity<?> loginFail() {
        return ResponseEntity.badRequest().body(null);
    }
}
