package com.rodkot.security.common;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticationConfig config;
    private final AuthenticationManager authenticationManager;

    public JwtTokenAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authenticationManager) {
        this.config = config;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = "";
        try {
       token = request.getHeader("Authorization");
            //token = request.getParameter("Authorization");
            if (token == "" || token==null) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (Exception e) {
        }

        if (token.isEmpty() || token == null || !token.startsWith("Bearer")) {

            filterChain.doFilter(request, response);
            return;
        }

        try {
            token = token.replace("Bearer ", "");
            Algorithm alg = Algorithm.HMAC256(config.getSecret().getBytes());
            JWTVerifier verifier = JWT.require(alg).build();
            DecodedJWT jwt = verifier.verify(token);

            String username = jwt.getSubject();

            Claim c = jwt.getClaim(config.getHeader().toLowerCase());
            String[] auths = c.asArray(String.class);

            Set<GrantedAuthority> ga = new HashSet<>();

            for (String auth : auths) {
                ga.add(new SimpleGrantedAuthority(auth));
            }

          //  String password = request.getParameter("password");
            Authentication authn = new UsernamePasswordAuthenticationToken(username, null, ga);
           // authenticationManager.authenticate(authn);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(authn);

        } catch (JWTVerificationException exception) {
            throw new ServletException(String.format("invalid token supplied:\n%s", token));

        }

        filterChain.doFilter(request, response);

    }

}
