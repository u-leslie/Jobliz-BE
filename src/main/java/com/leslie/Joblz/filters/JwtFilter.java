package com.leslie.Joblz.filters;

import com.leslie.Joblz.services.JwtService;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private static final Dotenv dotenv = Dotenv.load();
    private static final String jwtSecret = dotenv.get("SECRET_KEY");


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");


        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            System.out.println(jwt);
            String username = jwtService.extractUsername(jwt);
            String role = jwtService.extractAllClaims(jwt).get("role", String.class);
            System.out.println("Role" +role);
            System.out.println("Username"+username);


            try {

                if (username != null && role != null) {
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(username, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                System.out.println("JWT parsing error: " + e.getMessage());
                SecurityContextHolder.clearContext();
                e.printStackTrace();
            }
        } else {
            System.out.println("No Authorization header or Bearer token found.");
        }

        chain.doFilter(request, response);
    }
}



