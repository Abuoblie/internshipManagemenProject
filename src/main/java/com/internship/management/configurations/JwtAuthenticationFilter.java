package com.internship.management.configurations;

import com.internship.management.servicies.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor//this injection for all private final fields
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService; //this service is needed for the user data extraction from the token hence it will have all the needed method required
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,//request made by the user
            @NotNull HttpServletResponse response,//the response from the server to the request containing the payload. this enables to modify the payload or the header if needed
            @NotNull FilterChain filterChain//this enables us to create a chain of filter for the request
    ) throws ServletException, IOException {
        //part 1 check for token in the request header(the token can be found in the authorization part of the header
        final  String authHeader = request.getHeader("Authorization");
        final String jwt;
        String userEmail;

        if(authHeader ==null || !authHeader.startsWith("Bearer ")){
            //move on to the filter chain to the next request
            filterChain.doFilter(request,response);
            return;
        }
        //if token exist
        jwt = authHeader.substring(7);
        userEmail = jwtService.getUserName(jwt);

        //part with the token extracted from the header we check the user details
        //1 need to extract the user email or data from the token
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){//if we have the user email but the user is not authenticated
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);//we get the userdetails from the database via userdetailservise loadUserBy username method
            if (jwtService.isTokenValid(jwt,userDetails)){//if valid then update security context
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                //we created a new object authenticationToken with the userdetail received from the database
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));//we link our authenticationToken with the request
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);//we updated the security content holder with the authenticationToken
            }
            filterChain.doFilter(request,response);
        }


    }
}
