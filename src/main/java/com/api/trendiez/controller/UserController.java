package com.api.trendiez.controller;
import com.api.trendiez.models.AuthResponse;
import com.api.trendiez.models.User;
import com.api.trendiez.services.UserService;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Base64;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${jwt.secret}")
    private String jwtSecret;

    private final UserService userService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        User existingUser = userService.findByEmail(email);

        if (existingUser != null) {
            System.out.println(existingUser);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account already exists");
        } else {
            User user = new User(email,bCryptPasswordEncoder.encode(password));
            userService.save(user);
            // Generate a 512-bit key
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String jwtSecret = Base64.getEncoder().encodeToString(key.getEncoded());

            String token = Jwts.builder()
                    .setSubject(user.getId())
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
            return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(user, token));
        }
    }
    @GetMapping("/{uid}")
    public ResponseEntity<?> getUser(@PathVariable String uid) {
        User user = userService.findById(uid);
        if (user != null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        User user = userService.findByEmail(email);
        if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
            // Generate a 512-bit key
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
            String jwtSecret = Base64.getEncoder().encodeToString(key.getEncoded());

            String token = Jwts.builder()
                    .setSubject(user.getId())
                    .signWith(SignatureAlgorithm.HS512, jwtSecret)
                    .compact();
            return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(user, token));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
        }
    }

    // Implement other endpoints similarly
    // @PostMapping("/registerWithGoogle")
    // @PostMapping("/login")
    // @PostMapping("/loginWithGoogle")
    // @PostMapping("/passwordReset")
    // @PatchMapping("/")
    // @DeleteMapping("/")
    // @GetMapping("/")
    // @GetMapping("/user/{uid}")
    // @PostMapping("/location")
    // @PostMapping("/fcmToken")
}
