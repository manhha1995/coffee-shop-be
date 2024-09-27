package com.dap.coffee.auth.service;

import com.dap.coffee.auth.entities.User;
import com.dap.coffee.auth.repository.AuthRepository;
import com.dap.coffee.auth.utils.JwtTokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final AuthRepository authRepository;

    private final JwtTokenUtils jwtTokenUtil;


    public void removeToken(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String id;
        String userName;
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            userName = jwtTokenUtil.getUsernameFromRequest(request);
            Optional<User> result = authRepository.getByUserName(userName);
            if (result.isPresent()) {
                id = result.get().getId();
            }
        }
    }
}
