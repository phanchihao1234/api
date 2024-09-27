package com.example.spring_api.controllers;

import com.example.spring_api.models.User;
import com.example.spring_api.responses.ApiResponse;
import com.example.spring_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/user")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> index(){
        ApiResponse apiResponse = ApiResponse.builder()
                .data(userService.getAllUsers())
                .message("get all successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
    @GetMapping("/login")
    public String login(){
        return "login user";
    }
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Validated @RequestBody User user, BindingResult result){
        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).toList();
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(errors)
                    .message("Validation failed")
                    .status(HttpStatus.BAD_REQUEST.value())
                    .build();
            return ResponseEntity.badRequest().body(apiResponse);
        }
        User user1 = userService.createUser(user);
        ApiResponse apiResponse = ApiResponse.builder()
                .data(user1)
                .message("Insert Successfully")
                .status(HttpStatus.OK.value())
                .build();
        return ResponseEntity.ok().body(apiResponse);
    }
}
