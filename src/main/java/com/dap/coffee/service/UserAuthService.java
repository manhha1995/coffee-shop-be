package com.dap.coffee.service;

import com.dap.coffee.entities.User;
import com.dap.coffee.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthService {
    private final AuthRepository repository;

    public Optional<User> findUserByUserName(String userName){
        return repository.getByUserName(userName);
    }
}
