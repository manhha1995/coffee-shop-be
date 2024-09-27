package com.dap.coffee.auth.repository;

import com.dap.coffee.auth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User,String> {
    Optional<User> getByUserName(String userName);
}
