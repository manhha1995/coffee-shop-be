package com.dap.coffee.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    @NotBlank(message = "User Name must not Blank")
    private String userName;
    @NotBlank(message = "Password must not Blank")
    private String password;
}
