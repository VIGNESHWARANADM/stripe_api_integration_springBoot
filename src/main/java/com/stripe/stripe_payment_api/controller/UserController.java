package com.stripe.stripe_payment_api.controller;

import com.stripe.stripe_payment_api.dto.UserRequestDto;
import com.stripe.stripe_payment_api.dto.UserResponseDto;
import com.stripe.stripe_payment_api.model.User;
import com.stripe.stripe_payment_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(
            @RequestBody UserRequestDto userRequestDto
    ){
        UserResponseDto userResponseDto = userService.insert(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(
            @RequestBody UserRequestDto userRequestDto
    ){
        UserResponseDto userResponseDto = userService.isCredValid(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }
}
