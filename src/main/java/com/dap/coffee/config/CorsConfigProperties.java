package com.dap.coffee.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

@Data
@ConfigurationProperties(
        prefix = "filter",
        ignoreUnknownFields = false
)
@Configuration
public class CorsConfigProperties {

    private CorsConfiguration cors;

}
