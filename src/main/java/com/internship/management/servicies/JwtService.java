package com.internship.management.servicies;

import com.internship.management.configurations.KeyConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtService {
    private final KeyConfig config;



    //the first step in getting the user data from the token is extracting the claims from the token
    private Claims getAllclaims(String token){
       return Jwts.parserBuilder()
               .setSigningKey(config.getKEY())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }
    //the second step in getting the user data from the token is creating a generic methods that enables us to extract any claim from the claims
    public <T>T getClaim(String token, Function<Claims, T> claimsResolver)//the function claimsResolver is of type Claims and returns T
    {
         final Claims claims = getAllclaims(token);

         return claimsResolver.apply(claims);

    }
    //final step is to use the getclaim method to get the user information via the Cliams function getSubject
    public String getUserName(String token) {
        return getClaim(token, Claims::getSubject); //the getsubject function retuns the user info
    }

    //let generate the token
    public  String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+config.getEXPIRATIONTIME()))
                .signWith(config.getKEY())
                .compact();
    }
    public  String generateToken( UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    //next we will create methods that will check the validity of the token

    public boolean isTokenValid(String token, UserDetails userDetails){
        final  String username = getUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return  getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
       return getClaim(token,Claims::getExpiration);
    }


}
