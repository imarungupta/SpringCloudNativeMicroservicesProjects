package com.spring.cloud.employee.api.configuration;

import com.netflix.discovery.converters.Auto;
import com.spring.cloud.employee.api.service.JwtService;
import com.spring.cloud.employee.api.usernameandclaimfromtoken.UserNameAndClaimFromToken;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    String jtwToken = null;
    String userNameFromToken =null;
    @Autowired
    private UserNameAndClaimFromToken userNameAndClaimFromToken;
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest filterRequest, HttpServletResponse filterResponse,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Take headers from the request because we used to send token in the request header Authorization
        String authorizationHeader = filterRequest.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            // Now retrieve token from the header and remove Bearer from the token
            jtwToken = authorizationHeader.substring(7);

            // Now let's retrieve the username
            try {

                userNameFromToken = userNameAndClaimFromToken.getUserNameFromToken(jtwToken);

            } catch (IllegalArgumentException illegalArgumentException) {
                throw new IllegalArgumentException("Unable to get JWT token");
            } catch (ExpiredJwtException expiredJwtException) {
                System.out.println("Token is expired");
            }
        }else {
            System.out.println("Token does not start with Bearer");
        }
        if(userNameFromToken!=null && SecurityContextHolder.getContext().getAuthentication()==null){

            UserDetails userDetails = jwtService.loadUserByUsername(userNameFromToken);
            boolean validateToken = userNameAndClaimFromToken.validateToken(jtwToken, userDetails);

            if (validateToken) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(filterRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(filterRequest,filterResponse);
    }
}
