package com.jamilis.login.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jamilis.login.entity.UserEntity;
import com.jamilis.login.repository.IUserRepository;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expiration-time:300000}")
    private Long expirationTime;

    @Autowired
    private IUserRepository userRepository;

    public UserAuthenticationProvider(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * Create login token by email
     * @param email email
     * @return login token
     */
    public String createToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationTime);
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withIssuer(email)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(algorithm);
    }

    /**
     * Validate login token
     * @param token
     * @return
     */
    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);
        UserEntity user = userRepository.findByEmailAndIsActiveTrue(decoded.getIssuer())
                .orElse(null);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    /**
     * Get BCI user by token
     * @param token token
     * @return user
     */
    public UserEntity getUserByToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decoded = verifier.verify(token);
        return userRepository.findByEmailAndIsActiveTrue(decoded.getIssuer())
                .orElse(null);
    }

}
