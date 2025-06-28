package com.dashboard_erp.backend.Config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dashboard_erp.backend.DTO.UserDto;
import com.dashboard_erp.backend.Entity.User;
import com.dashboard_erp.backend.Mapper.UserMapper;
import com.dashboard_erp.backend.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretkey;
    @PostConstruct
    protected void init() {
        secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());

    }
    public String createToken(UserDto user) {
       Date now = new Date();
       Date validity = new Date(now.getTime() + 3_600_000L);
        return JWT.create()
                .withIssuer(user.getEmail())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName",user.getFirstName())
                .withClaim("lastName",user.getLastName())
                .sign(Algorithm.HMAC256(secretkey));
    }
    public Authentication validateToken(String token) {
       Algorithm algorithm= Algorithm.HMAC256(secretkey);
       JWTVerifier verifier=JWT.require(algorithm).build();
       DecodedJWT decoded=verifier.verify(token);
      UserDto user= UserDto.builder()
               .email(decoded.getIssuer())
               .firstName(decoded.getClaim("firstName").asString())
               .lastName(decoded.getClaim("lastName").asString())
               .build();
      return new UsernamePasswordAuthenticationToken(user,null, Collections.emptyList());
    }
    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm= Algorithm.HMAC256(secretkey);
        JWTVerifier verifier=JWT.require(algorithm).build();
        DecodedJWT decoded=verifier.verify(token);
        User user=userRepository.findByEmail(decoded.getIssuer())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user),null,Collections.emptyList());
    }
}
