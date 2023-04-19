package com.educative.ecommerce.controller;

import com.educative.ecommerce.common.Transaction;
import com.educative.ecommerce.common.command.Command;
import com.educative.ecommerce.dto.ResponseDto;
import com.educative.ecommerce.dto.user.SignInDto;
import com.educative.ecommerce.dto.user.SignInReponseDto;
import com.educative.ecommerce.dto.user.SignupDto;
import com.educative.ecommerce.exceptions.AuthenticationFailException;
import com.educative.ecommerce.model.User;
import com.educative.ecommerce.repository.MyServerRepository;
import com.educative.ecommerce.repository.UserRepository;
import com.educative.ecommerce.service.AuthenticationService;
import com.educative.ecommerce.service.RestService;
import com.educative.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    private MyServerRepository myServer;

    @Autowired
    RestService restService;

    @GetMapping("/all")
    public List<User> findAllUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        return userRepository.findAll();
    }

    // signup
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
        return userService.signUp(signupDto);
    }


    // signin
    @PostMapping("/signIn")
    public SignInReponseDto signIn(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }


}
