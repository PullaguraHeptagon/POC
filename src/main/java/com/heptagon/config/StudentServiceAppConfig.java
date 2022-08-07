package com.heptagon.config;

import com.heptagon.security.AuthTokenFilter;
import com.heptagon.security.JwtInterceptor;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.security.SecurityRequirement;


@Configuration
@EnableWebSecurity
public class StudentServiceAppConfig implements WebMvcConfigurer {

    @Autowired
    JwtInterceptor jwtInterceptor;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }


    @Bean
    public MappingJackson2HttpMessageConverter customHttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").password("{noop}password");
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${build.app.description}") String appDescription,
                                 @Value("${build.version}") String appVersion) {
        final String securitySchemeName = "bearerAuth";
        /*return new OpenAPI().components(new Components()
                .addSecuritySchemes("Authorization",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info((new Info())
                .title("Student API")
                .version(appVersion)
                .description(appDescription)
                .termsOfService("")
                .license((new License()).name("Copyright (c) 2022. Heptagon. All rights reserved")));*/
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**", "/otp/**").excludePathPatterns("/actuator/**", "/h2-console/**",
                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html", "/signup/**", "/signin/**");
    }
}
