package com.spring.cloud.employee.api.usernameandclaimfromtoken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class UserNameAndClaimFromToken {

    private static final String SECRET_KEY= "ThisismysecretKeyThisismysecretKeyThisismysecretKeyThisismysecretKeyThisismysecretKeyThisismysecretKeyThisismysecretKeyThisismysecretKeyThisismysecretKey";//UUID.randomUUID().toString(); // Generate random secret ksy

   // Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY),SignatureAlgorithm.HS256.getJcaName());
    private static final int TOKEN_VALIDITY=3600*5;
    private Claims getAllClaimsFromToken(String token){   // parseClaimsJws(token).getBody();

        Claims bodyClaim = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();

        return bodyClaim;
    }
    private<T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){

        Claims allClaimsFromToken = getAllClaimsFromToken(token);
        return claimResolver.apply(allClaimsFromToken);
    }
    // here in the above method we are using HigherOrder Function, which is nothing the function which either
    // takes the function as parameter or return a function

    public String getUserNameFromToken(String token){

        String getUserName = getClaimFromToken(token, Claims::getSubject);
        return getUserName;
    }

    public boolean validateToken(String token, UserDetails userDetails){

        String userNameFromToken = getUserNameFromToken(token);
        if(userNameFromToken.equals(userDetails.getUsername()) && !isTokenExpired(token)){
            return true;
        }else{
            return false;
        }
    }
    private boolean isTokenExpired(String token){
       final Date expirationDateFromToken = getExpirationDateFromToken(token);
       return expirationDateFromToken.before(new Date());
    }
    private Date getExpirationDateFromToken(String token){

        Date  tokenExpirationDate = getClaimFromToken(token, Claims::getExpiration);
        return tokenExpirationDate;
    }

    // Method to generate token based on username
    public String generateTokenFromUserDetail(UserDetails userDetails){

        Map<String,Object> claims= new HashMap<>();

        String compactJwtToken = Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();

        return compactJwtToken;
    }
}
// .signWith(SignatureAlgorithm.HS256, SECRET_KEY)

//https://www.viralpatel.net/java-create-validate-jwt-token/