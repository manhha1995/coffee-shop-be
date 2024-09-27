package com.dap.coffee.auth.model.request;

import jakarta.validation.constraints.NotBlank;
import lgcns.dap.core.model.RequestData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {
    @NotBlank(message = "Refresh token must not Blank")
    private String refreshToken;
}
