package com.dap.coffee.model.request;

import com.dap.coffee.entities.ERole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationUserRequest {
    @NotBlank(message = "User Name must not blank")
    @Length(min = 3, max = 32, message = "User Name length not valid ( Min = 3, Max = 32")
    private String userName;
    @NotBlank(message = "Password must not blank")
    @Length(min = 6, max = 16, message = "password length not valid ( Min = 6 , Max = 16)")
    private String password;
    private String phoneNumber;
    @NotNull(message = "Role must not null")
    private ERole role;
}
