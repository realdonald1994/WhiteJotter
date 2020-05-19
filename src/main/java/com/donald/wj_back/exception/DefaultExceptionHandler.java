package com.donald.wj_back.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Donald
 * @data 19/05/2020 16:06
 */
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> handleAuthorizationException(UnauthorizedException e){
        String message = "Authorization failed";
        return new ResponseEntity(new Error(message),HttpStatus.UNAUTHORIZED);
    }
}
