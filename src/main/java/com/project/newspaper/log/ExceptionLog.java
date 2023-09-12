package com.project.newspaper.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionLog extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionLog.class);

    @ExceptionHandler(Exception.class)
    public void handleGlobalException(Exception ex) {
        // Log the exception
        logger.error("EXCEPTION: an exception occurred " + ex.getMessage(), ex);
    }
}
