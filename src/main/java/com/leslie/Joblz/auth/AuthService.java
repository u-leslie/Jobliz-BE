package com.leslie.Joblz.auth;
import com.leslie.Joblz.dtos.LoginRequest;
import com.leslie.Joblz.dtos.LoginResponse;
import com.leslie.Joblz.entities.User;
import com.leslie.Joblz.repositories.UserRepository;
import com.leslie.Joblz.services.JwtService;
import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthService {

      private final UserRepository userRepository;
      private final PasswordEncoder passwordEncoder;
      private static final Dotenv dotenv = Dotenv.load();
      private static final String jwtSecret = dotenv.get("SECRET_KEY");
      private final long jwtExpirationMs = 86400000L;
      private final JwtService jwtService;


      public LoginResponse login(LoginRequest request) {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

            if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                  throw new IllegalArgumentException("Invalid email or password");
            }
            String token = jwtService.generateToken(user,user.getRole());
            return new LoginResponse(token, user.getRole());
      }


}
