package com.rodkot.security.common;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtAuthenticationConfig config;
    private final ObjectMapper mapper;

    public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(config.getUrl(), "POST"));
        setAuthenticationManager(authManager);
        this.config = config;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
            throws AuthenticationException, IOException {
        User u = mapper.readValue(req.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                u.getUsername(), u.getPassword(), Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
                                            Authentication auth) {

        Algorithm algorithm = Algorithm.HMAC256(config.getSecret().getBytes());
        String access_token = JWT.create()
                .withSubject(auth.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+10000000 ))
                .withClaim(config.getHeader().toLowerCase(), auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList())).sign(algorithm);


        rsp.addHeader(config.getHeader(), config.getPrefix() + " " + access_token);
    }

    @Getter
    @Setter
    private static class User {
        private String username, password;
    }
}
