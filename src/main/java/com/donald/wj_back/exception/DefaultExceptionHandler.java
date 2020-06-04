package com.donald.wj_back.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Donald
 * @data 19/05/2020 16:06
 */
@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleAuthorizationException(Exception e){
        String message ;
        if (e instanceof IllegalArgumentException) {
            return ResponseEntity.badRequest().build();
        }
        else if (e instanceof MethodArgumentNotValidException) {
            message = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().build();
        }
        else{

            message = "Authorized Failed";
            return ResponseEntity.badRequest().build();

        }


    }
}
