package com.dap.coffee.auth.controller;

import com.dap.coffee.auth.model.request.UserLoginRequest;
import com.dap.coffee.auth.model.response.LoginResponse;
import com.dap.coffee.auth.service.AuthService;
import com.dap.coffee.common.ApiResponse;
import com.dap.coffee.exception.NotFoundEntityException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@Valid @RequestBody UserLoginRequest request) throws NotFoundEntityException {
        return authService.userLogin(request);
    }
}
