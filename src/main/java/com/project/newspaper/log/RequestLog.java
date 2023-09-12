package com.project.newspaper.log;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RequestLog implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(RequestLog.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Log request information
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        logger.info("REQUEST: received request {} {}", httpRequest.getMethod(), httpRequest.getRequestURI());

        // Continue the request chain
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
