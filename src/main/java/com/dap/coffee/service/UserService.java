package com.dap.coffee.service;

import com.dap.coffee.entities.Role;
import com.dap.coffee.entities.User;
import com.dap.coffee.exception.DuplicatedException;
import com.dap.coffee.exception.NotFoundEntityException;
import com.dap.coffee.model.request.RegistrationUserRequest;
import com.dap.coffee.model.response.RegistrationUserResponse;
import com.dap.coffee.repository.RoleRepository;
import com.dap.coffee.repository.UserRepository;
import com.dap.coffee.utils.HttpRequestUtil;
import com.dap.coffee.utils.ModelMapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public RegistrationUserResponse registration(RegistrationUserRequest request) {
        if (isUserNameExist(request.getUserName())) {
            throw DuplicatedException.of("User Name is exist");
        }
        User user = ModelMapperUtils.toObject(request, User.class);

        Role role = roleRepository.findRoleByRoleCode(request.getRole()).orElseThrow(() -> NotFoundEntityException.of("Role Not Found"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        user.setCreateBy(HttpRequestUtil.getUserName());
        user.setIsDeleted(false);
        return ModelMapperUtils.toObject(userRepository.save(user), RegistrationUserResponse.class);
    }

    private boolean isUserNameExist(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isPresent();
    }
}

