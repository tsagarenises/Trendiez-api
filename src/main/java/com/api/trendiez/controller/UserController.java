package com.api.trendiez.controller;
import com.api.trendiez.models.AuthResponse;
import com.api.trendiez.models.LoginRequest;
import com.api.trendiez.models.LoginResponse;
import com.api.trendiez.models.User;
import com.api.trendiez.services.UserService;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
//import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.JsonGenerator;
//import com.google.api.client.json.JsonParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.io.*;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.secret.2}")
    private String jwtSecret2;
    @Value("${GOOGLE_CLIENT_ID}")
    private String clientId;

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
    @PostMapping("/registerWithGoogle")
    public ResponseEntity<Object> SignUpWithGoogle(@RequestBody String token) {
        try {
          //  Claims claims = Jwts.parser().setSigningKeyResolver(SigningKeyResolver.secret(secretKey)).parseClaimsJws(token).getBody();
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtSecret2) // Provide your secret key here
                    .parseClaimsJws(token)
                    .getBody();
            // Extract user information from claims
            String userId = claims.getSubject();
            String email = (String) claims.get("email");
            String name = (String) claims.get("name");
            String picture = (String) claims.get("picture");

            // Find user by email
            User existingUser = userService.findByEmail(email);

            if (existingUser != null) {
                // Account already exists, return error
                return ResponseEntity.badRequest().body("Account already exists");
            } else {
                // Create new user
                User user = new User();
                user.setAuthenticationType("google");
                user.setPicture(picture);
                user.setEmail(email);
                user.setName(name);

                // Save user and generate JWT token
                User savedUser = userService.save(user);
                Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
                String jwtSecret = Base64.getEncoder().encodeToString(key.getEncoded());

                String jtoken = Jwts.builder()
                        .setSubject(savedUser.getId())
                        .signWith(SignatureAlgorithm.HS512, jwtSecret2)
                        .compact();

                // Return successful response with user and token
                return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse(savedUser, jtoken));
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid token, database errors)
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
//    @PostMapping("/loginWithGoogle")
//        public ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody LoginRequest request) throws Exception {
//            String idToken = request.getIdToken();
//            String userId = request.getUserId();
//
//            if (StringUtils.isEmpty(idToken)) {
//                return ResponseEntity.badRequest().body(new LoginResponse("ID Token is required"));
//            }
//
//            try {
//                // Google verification using library
//                HttpTransport httpTransport = new NetHttpTransport();
//                JsonFactory jsonFactory = new JsonFactory() {
//                    @Override
//                    public JsonParser createJsonParser(InputStream in) throws IOException {
//                        return null;
//                    }
//
//                    @Override
//                    public JsonParser createJsonParser(InputStream in, Charset charset) throws IOException {
//                        return null;
//                    }
//
//                    @Override
//                    public JsonParser createJsonParser(String value) throws IOException {
//                        return null;
//                    }
//
//                    @Override
//                    public JsonParser createJsonParser(Reader reader) throws IOException {
//                        return null;
//                    }
//
//                    @Override
//                    public JsonGenerator createJsonGenerator(OutputStream out, Charset enc) throws IOException {
//                        return null;
//                    }
//
//                    @Override
//                    public JsonGenerator createJsonGenerator(Writer writer) throws IOException {
//                        return null;
//                    }
//                };
//                GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
//                        .setAudience(Collections.singletonList(clientId)) // Replace with your client ID
//                        .build();
//
//                GoogleIdToken token = verifier.verify(idToken);
//                if (token != null) {
//                    GoogleIdToken.Payload payload = token.getPayload();
//
//                    // Print user identifier
//                    String usersId = payload.getSubject();
//                    String email = payload.getEmail();
//
//                    User user = userService.findByEmail(email);
//
//                    if (user != null) {
//                        String jtoken = Jwts.builder()
//                                .setSubject(user.getId())
//                                .signWith(SignatureAlgorithm.HS512, jwtSecret2)
//                                .compact();
//
//                        // Return successful response with user and token
//                        return ResponseEntity.ok(new LoginResponse(user, jtoken));
//                    } else {
//                        return ResponseEntity.badRequest().body(new LoginResponse("Invalid Credentials"));
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println(e.getMessage());
//                throw new Exception("Login failed");
//            }
//
//            return ResponseEntity.badRequest().body(new LoginResponse("Invalid Credentials"));
//        }


    @PostMapping("/generateJWToken")
    public String generateJwtToken(@RequestBody User user) {

        java.util.Date date = new java.util.Date();
        String jwtToken = Jwts.builder()
                .setSubject(user.getId()) // Set user ID as the subject
                .claim("userId", user.getId()) // Add additional claims (optional)
                .claim("email", user.getEmail())
                .claim("name", user.getName()) // Add additional claims (optional)
                .claim("picture", user.getPicture())
                .setIssuedAt(date) // Set issue time // Set expiration time (1 hour) // Adjust as needed
                .signWith(SignatureAlgorithm.HS256, jwtSecret2) // Sign with chosen algorithm and secret key
                .compact();
        return jwtToken;
    }
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
