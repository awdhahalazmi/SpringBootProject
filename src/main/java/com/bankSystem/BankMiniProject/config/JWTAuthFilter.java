package com.bankSystem.BankMiniProject.config;

import com.bankSystem.BankMiniProject.service.auth.CustomUserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class JWTAuthFilter extends OncePerRequestFilter {
    private static final String BEARER = "Bearer";

    private final JWTUtil jwtUtil;

    private final CustomUserService userDetailsService;



    public JWTAuthFilter(JWTUtil jwtUtil, CustomUserService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizatioinHeader = request.getHeader(AUTHORIZATION);
        if (request.getServletPath().equals("/api/v1/login") && authorizatioinHeader != null && authorizatioinHeader.startsWith(BEARER)) {
            String token = authorizatioinHeader.substring(7);
            if (jwtUtil.isTokenValid(token)) {
                String username = jwtUtil.getUsernameFromToken(token);
                if (username == null) {
                    throw new UsernameNotFoundException("User not found ");
                }

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource());
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        }
        filterChain.doFilter(request, response);
    }
}


