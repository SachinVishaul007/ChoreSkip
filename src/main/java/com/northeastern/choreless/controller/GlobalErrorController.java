package com.northeastern.choreless.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GlobalErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        // Log the error for debugging
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        Object exception = request.getAttribute("jakarta.servlet.error.exception");

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            System.out.println("Error Status Code: " + statusCode);
        }

        if (exception != null) {
            System.out.println("Error Exception: " + exception.toString());
        }

        return "error";
    }
}