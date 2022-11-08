package com.acme.gym4u.security.middleware;

import com.acme.gym4u.security.domain.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    @Autowired
    private JwtHandler handler;

    @Autowired
    private UserService userService;

    private String parseTokenFrom(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authorizationHeader) &&
                authorizationHeader.startsWith("Bearer"))
            return new LinkedList<>(Arrays.asList(authorizationHeader.split(" "))).getLast();
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = parseTokenFrom(request);
            if(token != null && handler.validateToken(token)) {

                logger.info("Token: {}", token);

                String username = handler.getUsernameFrom(token);

                UserDetails principal = userService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        principal, null, principal.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("User Authentication cannot be set: {}", e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
