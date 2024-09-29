package com.dap.coffee.controller;

import com.dap.coffee.model.request.UserLoginRequest;
import com.dap.coffee.model.response.LoginResponse;
import com.dap.coffee.service.AuthService;
import com.dap.coffee.common.ApiResponse;
import com.dap.coffee.exception.NotFoundEntityException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) throws NotFoundEntityException {
        return authService.userLogin(request);
    }
}
