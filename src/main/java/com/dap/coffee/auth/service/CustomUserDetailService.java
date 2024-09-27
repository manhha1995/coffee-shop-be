package com.dap.coffee.auth.service;

import com.dap.coffee.auth.entities.User;
import com.dap.coffee.auth.model.CustomUserDetails;
import com.dap.coffee.auth.repository.AuthRepository;
import com.dap.coffee.exception.NotFoundEntityException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AuthRepository authRepository;


    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.getByUserName(username).orElseThrow(() -> NotFoundEntityException.of("User details not found for user: " + username));
        return new CustomUserDetails(user);
    }
}
