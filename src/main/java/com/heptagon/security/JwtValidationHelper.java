package com.heptagon.security;

import com.heptagon.error.AuthFailureException;
import com.heptagon.error.ForbiddenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtValidationHelper {
    private static final Logger logger = LoggerFactory.getLogger(JwtValidationHelper.class);
    private static final String BEARER_TOKEN_PREFIX = "Bearer ";

    @Value("${jwt.enabled:true}")
    private Boolean isJwtValidationEnabled;

    public void validateIssuerAndExpiration(String authHeader, String keyInBase64, String issuer) throws ForbiddenException, AuthFailureException {
        if(!isJwtValidationEnabled) {
            return;
        }
        if(StringUtils.isAnyBlank(keyInBase64,issuer,authHeader) || (StringUtils.isNotBlank(authHeader) && (authHeader.length() < 8 || !authHeader.startsWith(BEARER_TOKEN_PREFIX)))) {
            throw  new ForbiddenException();
        }
        String token = authHeader.substring(7).trim();
        validateToken(token, keyInBase64);
    }

    private void validateToken(final String token, final String key) throws AuthFailureException {
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        } catch (Exception e) {
            logger.error("Failed to parse JWT", e);
            if(claimsJws == null) {
                logger.error("After parsing JWT, there are no claims for JWT token.");
                throw new AuthFailureException();
            }
        }
    }
}
