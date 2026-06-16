package com.RideMatch.rider.util;

import com.RideMatch.rider.entity.Principle;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.Principal;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal");
        String authHeader =  request.getHeader("Authorization");

        System.out.println("Authorization Header = " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            System.out.println("Token = " + token);

            try{
                if(jwtUtil.validateToken(token)){

                String username = jwtUtil.extractUsername(token);
                Long id = jwtUtil.extractId(token);

                if(SecurityContextHolder.getContext().getAuthentication() == null) {
                    Principle principle = Principle.builder()
                            .id(id)
                            .username(username)
                            .build();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(principle , null,java.util.Collections.emptyList());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    System.out.println(
                            "Authentication = " +
                                    SecurityContextHolder.getContext().getAuthentication()
                    );
                }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        filterChain.doFilter(request, response);
    }
}
