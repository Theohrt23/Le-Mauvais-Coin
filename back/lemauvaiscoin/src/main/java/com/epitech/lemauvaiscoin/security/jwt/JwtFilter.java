package com.epitech.lemauvaiscoin.security.jwt;

import com.epitech.lemauvaiscoin.service.IJwtService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String HEADER_BEARER_TOKEN = "Bearer";
    private final static Integer HEADER_BEARER_TOKEN_SLICE = HEADER_BEARER_TOKEN.length() + 1;

    private final IJwtService jwtService;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = extractJwtFromHeader(request);
            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                String username = jwtService.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromHeader(HttpServletRequest request) {
        String headerAuth = request.getHeader(HEADER_AUTHORIZATION);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(HEADER_BEARER_TOKEN)) {
            return headerAuth.substring(HEADER_BEARER_TOKEN_SLICE);
        }
        return null;
    }
}
