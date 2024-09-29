package com.dap.coffee.service;

import com.dap.coffee.entities.User;
import com.dap.coffee.model.response.CustomUserDetails;
import com.dap.coffee.repository.AuthRepository;
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
