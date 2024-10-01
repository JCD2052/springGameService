package org.jcd2052.api.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.constants.JwtConstants;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JwtConstants.AUTHORIZATION_HEADER);

        if (authHeader == null || !authHeader.startsWith(JwtConstants.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = StringUtils.substringAfter(authHeader, JwtConstants.BEARER);
            String username = jwtUtils.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (username != null && authentication == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if (jwtUtils.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            userDetails.getPassword(),
                            userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }
}