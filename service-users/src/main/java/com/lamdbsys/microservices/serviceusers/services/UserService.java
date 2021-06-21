package com.lamdbsys.microservices.serviceusers.services;

import com.lamdbsys.microservices.serviceusers.dtos.CredentialsDto;
import com.lamdbsys.microservices.serviceusers.dtos.UserDto;
import com.lamdbsys.microservices.serviceusers.entities.User;
import com.lamdbsys.microservices.serviceusers.exceptions.ApplicationException;
import com.lamdbsys.microservices.serviceusers.mappers.UserMapper;
import com.lamdbsys.microservices.serviceusers.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.duration:duration}")
    private Duration duration;

    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public UserDto signin(CredentialsDto credentialsDto) {

        final var user =
                this.userRepository.findByUsername(credentialsDto.getUsername())
                        .orElseThrow(() -> new ApplicationException("User not found", HttpStatus.NOT_FOUND));

        final var matches = this.passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword());

        if (matches) {
            return userMapper.toDto(user, createToken(user));
        } else {
            throw new ApplicationException("Invalid password", HttpStatus.BAD_REQUEST);
        }
    }

    public UserDto validateToken(final String token) {

        final var username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        final var user = this.userRepository.findByUsername(username);

        return user.map(u -> userMapper.toDto(u, token))
                .orElseThrow(() -> {
                    throw new ApplicationException("User not found.", HttpStatus.NOT_FOUND);
                });

    }

    private String createToken(final User user) {

        final var claims = Jwts.claims().setSubject(user.getUsername());
        final var now = LocalDateTime.now();
        final var validity = now.plusNanos(duration.toNanos());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(toDate(now))
                .setExpiration(toDate(validity))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
