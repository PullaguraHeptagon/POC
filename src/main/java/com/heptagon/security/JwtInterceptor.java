package com.heptagon.security;

import com.heptagon.error.AuthFailureException;
import com.heptagon.error.ForbiddenException;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            jwtValidationHelper.validateIssuerAndExpiration(authHeader,
                    jwtProperties.getSignature().get("key"),
                    jwtProperties.getSignature().get("issuer"));
        } catch (ForbiddenException e) {
            logger.error("Security Exception :: Http Status {} for method {}, uri {} ",
                    HttpServletResponse.SC_FORBIDDEN, request.getMethod(), request.getRequestURI(), e);
            throw new ForbiddenException();
        } catch (AuthFailureException e) {
            logger.error("Security Exception :: Http Status {} for method {}, uri {} ",
                    HttpServletResponse.SC_UNAUTHORIZED, request.getMethod(), request.getRequestURI(), e);
            throw new AuthFailureException();
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception e) {
        logger.info("afterCompletion uri = {}, method = {}, headers = {}, status code = {} ", request.getRequestURI(),
                request.getMethod(), request.getHeaderNames(), response.getStatus());
    }
}
