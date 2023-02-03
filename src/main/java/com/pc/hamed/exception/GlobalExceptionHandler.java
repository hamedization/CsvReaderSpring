package com.pc.hamed.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessError> customHandleNotFound(Exception ex) {

        BusinessException be = (BusinessException) ex;
        BusinessError businessError = new BusinessError(be.getHttpStatus(), be.getErrorMessage());
        return new ResponseEntity<>(businessError, be.getHttpStatus());

    }


}
