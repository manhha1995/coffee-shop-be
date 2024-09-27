package com.dap.coffee.auth.service;

import com.dap.coffee.auth.entities.User;
import com.dap.coffee.auth.model.CustomUserDetails;
import com.dap.coffee.auth.model.request.RefreshTokenRequest;
import com.dap.coffee.auth.model.request.UserLoginRequest;
import com.dap.coffee.auth.model.response.LoginResponse;
import com.dap.coffee.auth.utils.JwtTokenUtils;
import com.dap.coffee.common.ApiResponse;
import com.dap.coffee.common.MessageResponse;
import com.dap.coffee.exception.NotFoundEntityException;
import com.dap.coffee.exception.TokenException;
import com.dap.coffee.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserService service;
    private final JwtTokenUtils jwtTokenUtil;

    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public ApiResponse<LoginResponse> userLogin(UserLoginRequest request) throws NotFoundEntityException {
        logger.info("-------------- User login ---------------------");
        User user = service.findUserByUserName(request.getUserName()).orElseThrow(() -> NotFoundEntityException.of("User Not Found"));
        if (Objects.nonNull(user.getIsDeleted()) && Boolean.TRUE.equals(user.getIsDeleted())) {
            throw UnauthorizedException.of("User invalid");
        }
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        Date date = new Date(System.currentTimeMillis() + JwtTokenUtils.JWT_TOKEN_VALIDITY);
        return ApiResponse.ok(MessageResponse.SUCCESS, new LoginResponse(jwtToken, refreshToken, user.getId(), formatter.format(date)));
    }

    public LoginResponse generateTokenFromRefreshToken(RefreshTokenRequest request) throws TokenException {
        if (jwtTokenUtil.validateRefreshToken(request.getRefreshToken())) {
            String jwtToken = jwtTokenUtil.generateToken(request.getRefreshToken());
            String userId = jwtTokenUtil.getUserIdFromToken(jwtToken);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            Date date = new Date(System.currentTimeMillis() + JwtTokenUtils.JWT_TOKEN_VALIDITY);
            return new LoginResponse(jwtToken, request.getRefreshToken(), userId, formatter.format(date));
        }
        throw new TokenException("Refresh token is invalid");
    }
}
