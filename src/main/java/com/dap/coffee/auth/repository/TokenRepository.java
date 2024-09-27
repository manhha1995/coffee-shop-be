package com.dap.coffee.auth.repository;

import com.dap.coffee.auth.entities.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, String> {
}
