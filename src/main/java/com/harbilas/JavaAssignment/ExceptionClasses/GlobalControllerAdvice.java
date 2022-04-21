package com.harbilas.JavaAssignment.ExceptionClasses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(NoDepartmentExists.class)
    public ResponseEntity<String> handleNoDepartment(NoDepartmentExists noDepartmentExists){
        return new ResponseEntity<String>(noDepartmentExists.getMessage(), HttpStatus.NOT_FOUND);
    }
}
