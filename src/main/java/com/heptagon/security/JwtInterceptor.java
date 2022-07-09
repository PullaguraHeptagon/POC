package com.heptagon.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(JwtInterceptor.class);
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String ERROR_STATUS = "error_status";

    @Autowired
    JwtValidationHelper jwtValidationHelper;

    @Autowired
    JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            jwtValidationHelper.validateIssuerAndExpiration(authHeader,
                    jwtProperties.getSignature().get("key"),
                    jwtProperties.getSignature().get("issuer"));
        } catch (Exception e) {
            request.setAttribute(ERROR_STATUS, response);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            logger.error("Security Exception :: Http Status {} for method {}, uri {} ",
                    HttpServletResponse.SC_FORBIDDEN, request.getMethod(), request.getRequestURI(), e);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) {
        logger.info("afterCompletion uri = {}, method = {}, headers = {}, status code = {} ", request.getRequestURI(),
                request.getMethod(), request.getHeaderNames(), response.getStatus());
    }
}
