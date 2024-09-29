package com.dap.coffee.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Slf4j
public class WebConfigurer implements ServletContextInitializer {
    private final Environment env;
    private final CorsConfigProperties corsConfigProperties;

    public WebConfigurer(Environment env, CorsConfigProperties corsConfigProperties) {
        this.env = env;
        this.corsConfigProperties = corsConfigProperties;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        if (env.getActiveProfiles().length != 0) {
            log.info("Web application configuration, using profiles: {}", (Object[]) env.getActiveProfiles());
        }
        log.info("Web application fully configured");
    }
}
