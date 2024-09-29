package com.dap.coffee.model.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenResponse {

    @NotBlank
    private String userId;
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private Date expiryAcessTokenDate;
    @NotBlank
    private Date expiryRefreshTokenDate;
}
