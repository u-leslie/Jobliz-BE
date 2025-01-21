package com.leslie.Joblz.auth;

import com.leslie.Joblz.dtos.LoginRequest;
import com.leslie.Joblz.dtos.LoginResponse;
import com.leslie.Joblz.dtos.UserDto;
import com.leslie.Joblz.entities.User;
import com.leslie.Joblz.repositories.UserRepository;
import com.leslie.Joblz.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.leslie.Joblz.dtos.UserDto;

import java.security.Key;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthService {

      private final UserRepository userRepository;
      private final PasswordEncoder passwordEncoder;
      private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS256);
      private final long jwtExpirationMs = 86400000L;

      public LoginResponse login(LoginRequest request) {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                  throw new IllegalArgumentException("Invalid email or password");
            }

            String token = generateToken(user);

            return new LoginResponse(token, user.getRole());
      }

      private String generateToken(User user) {
            return Jwts.builder()
                    .setSubject(user.getEmail())
                    .claim("role", user.getRole())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                    .signWith(SignatureAlgorithm.HS256, jwtSecret)
                    .compact();
      }
}
