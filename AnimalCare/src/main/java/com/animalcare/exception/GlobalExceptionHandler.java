package com.animalcare.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "You are not authorized to access this resource.");
        mav.addObject("status", HttpStatus.FORBIDDEN.value());
        mav.setViewName("error");
        return mav;
    }

    // other exception handlers
}
