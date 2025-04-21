package com.powerup.realestate.properties.infrastructure.security.jwt;


import com.auth0.jwt.interfaces.Claim;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
@Slf4j
@Component
public class JwtUtils {

    @Value("${SECURITY_JWT_KEY_PRIVATE}")
    private String privateKey;

    @Value("${SECURITY_JWT_KEY_USER_GENERATOR}")
    private String userGenerator;

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        return JWT.require(algorithm)
                .build()
                .verify(token);
    }

    public String extractEmail(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }
}