package com.dap.coffee.auth.service;

import com.dap.coffee.auth.entities.User;
import com.dap.coffee.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final AuthRepository repository;

    public Optional<User> findUserByUserName(String userName){
        return repository.getByUserName(userName);
    }
}
