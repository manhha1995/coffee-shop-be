package com.dap.coffee.auth.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String jwtToken;
    private String refreshToken;
    private String userId;
    private String expireDate;
}
