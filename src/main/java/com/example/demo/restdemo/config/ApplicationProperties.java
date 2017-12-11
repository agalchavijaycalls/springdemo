package com.example.demo.restdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

@Component
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Security security = new Security();

    private final CorsConfiguration cors = new CorsConfiguration();

    public static class Security {

        private final Authentication authentication = new Authentication();

        public static class Authentication {

            private final Jwt jwt = new Jwt();

            public static class Jwt {

                private String secret;

                private long tokenValidityInSeconds = 600L;

                private long refreshTokenValidityInSeconds = 900L;

                public String getSecret() {
                    return secret;
                }

                public void setSecret(String secret) {
                    this.secret = secret;
                }

                public long getTokenValidityInSeconds() {
                    return tokenValidityInSeconds;
                }

                public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                    this.tokenValidityInSeconds = tokenValidityInSeconds;
                }

                public long getRefreshTokenValidityInSeconds() {
                    return refreshTokenValidityInSeconds;
                }

                public void setRefreshTokenValidityInSeconds(long refreshTokenValidityInSeconds) {
                    this.refreshTokenValidityInSeconds = refreshTokenValidityInSeconds;
                }
            }

            public Jwt getJwt() {
                return jwt;
            }
        }

        public Authentication getAuthentication() {
            return authentication;
        }
    }

    public Security getSecurity() {
        return security;
    }

    public CorsConfiguration getCors() {
        return cors;
    }
}
