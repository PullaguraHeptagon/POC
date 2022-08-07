package com.heptagon.error;

import com.heptagon.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomAuthenticationFailureHandler {

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> onForbidden() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.FORBIDDEN);
        errorMessage.setMessage("Forbidden. Please try later..");
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = AuthFailureException.class)
    public ResponseEntity<Object> onAuthenticationFailure() {
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setStatus(HttpStatus.UNAUTHORIZED);
        errorMessage.setMessage("Invalid credentials. Please try again later..");
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }
}
