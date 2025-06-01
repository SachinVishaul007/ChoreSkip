package com.northeastern.choreless.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, HttpServletRequest request) {
        // Log the exception
        System.out.println("Unhandled Exception: " + ex.getMessage());
        ex.printStackTrace();
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        System.out.println("Runtime Exception: " + ex.getMessage());
        ex.printStackTrace();
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
        System.out.println("Null Pointer Exception: " + ex.getMessage());
        ex.printStackTrace();
        return "error";
    }
}