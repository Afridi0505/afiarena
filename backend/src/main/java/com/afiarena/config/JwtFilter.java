package com.afiarena.config;

import com.afiarena.service.GamesUserDetailsService;
import com.afiarena.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    JwtService jwtService;

    @Autowired
    GamesUserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization"); /* first get the authorization header*/
        String token = null;
        String username = null;

        if(authHeader !=null && authHeader.startsWith("Bearer ")) { /* find with Bearer keyword and exclude it and get username,
        because we will find whether the username is present in the bearer or not */
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) { /* if token not validated then we run validateToken method */
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            /* get userObject with passing username */
            if(jwtService.isTokenValid(token, userDetails)){ /* if userDetails are valid in the token then return the token to the upaf */
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); /* authToekn is ready and pass it to the context */
                SecurityContextHolder.getContext().setAuthentication(authToken); /* we are adding the token in the chain in security context */

            }
        }
        filterChain.doFilter(request, response);
    }
}
