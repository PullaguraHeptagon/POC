package com.heptagon.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "jwt.server")
@Setter
@Getter
public class JwtProperties {
    private Map<String, String> signature;
}
