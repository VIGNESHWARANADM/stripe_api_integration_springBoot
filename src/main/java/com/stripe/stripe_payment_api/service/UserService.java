package com.stripe.stripe_payment_api.service;

import com.stripe.stripe_payment_api.respository.UserRespository;
import com.stripe.stripe_payment_api.dto.UserRequestDto;
import com.stripe.stripe_payment_api.dto.UserResponseDto;
import com.stripe.stripe_payment_api.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRespository userRespository;

    public UserService(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    public UserResponseDto insert(UserRequestDto userRequestDto){
        System.out.println(userRequestDto);
        System.out.println(userRequestDto.getEmail() + " " + "email");
        if(userRespository.existsByEmail(userRequestDto.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRespository.save(user);
        return new UserResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                "User registered successfully"
        );
    }

    public UserResponseDto isCredValid(UserRequestDto userRequestDto){
        User retrieved = userRespository.existsByEmailAndPassword(
                userRequestDto.getEmail(),userRequestDto.getPassword());
        if(retrieved == null){
            throw new RuntimeException("InValid Creditionals");
        }
        return new UserResponseDto(
                retrieved.getId(),
                retrieved.getName(),
                retrieved.getEmail(),
                "Logged In successfully"
        );
    }


}
