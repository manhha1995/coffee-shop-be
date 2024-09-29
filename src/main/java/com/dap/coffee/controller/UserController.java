package com.dap.coffee.controller;

import com.dap.coffee.model.request.RegistrationUserRequest;
import com.dap.coffee.model.response.RegistrationUserResponse;
import com.dap.coffee.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public RegistrationUserResponse registrationUser(@Valid @RequestBody RegistrationUserRequest request) {
        return userService.registration(request);
    }
}
