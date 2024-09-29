package com.dap.coffee.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.ws.rs.core.HttpHeaders;
import java.util.Base64;
import java.util.List;

@Slf4j
public class HttpRequestUtil {

    private HttpRequestUtil() {}

    public static final String DEFAULT_USERNAME = "system";

    public static HttpServletRequest getCurrentHttpRequest() {
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        if (requestAttribute instanceof ServletRequestAttributes) {
            return ((ServletRequestAttributes) requestAttribute).getRequest();
        }
        log.debug("Not called in the context of an HTTP Request");
        return null;
    }

    public static String getUserName() {
        HttpServletRequest currentRequest = getCurrentHttpRequest();
        if (currentRequest != null) {
            final String requestTokenHeader = currentRequest.getHeader(HttpHeaders.AUTHORIZATION);
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
                String jwtToken = requestTokenHeader.substring(7);
                String userName = getUserName(jwtToken);
                if (StringUtils.isBlank(userName)) {
                    return userName;
                }

            }
        }
        return DEFAULT_USERNAME;
    }

    public static String getUserName(String bearerToken) {
        try {
            String[] splitString = bearerToken.split("\\.");
            String base64EncodedBody = splitString[1];
            String body = new String(Base64.getDecoder().decode(base64EncodedBody));
            UserInfo userInfo = jsonToObj(body, UserInfo.class);
            if (userInfo != null)
                return userInfo.getUserName();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
        return null;
    }

    public static <T> T jsonToObj(String json, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }


    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UserInfo {

        private String userName;
        private String userId;
        private List<String> authorities;
    }
}
