package mx.com.pandadevs.pibeapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtAuth {

    @Value("${secret}")
    private String KEY;

    public String createToken(UserDetails userDetails){

        //Adding subject firm
        Claims userRoleClaims = Jwts.claims().setSubject(userDetails.getUsername());
        //Adding authority roles to jwt claims
        userRoleClaims.put("authorities", userDetails.getAuthorities());

        return Jwts.builder()
                .setIssuer("PIBE")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();

    }

    public boolean validarToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(getUsername(token)) && !isTokenExpired(token);
    }

    public String getUsername(String encodedToken){
        return decodeToken(encodedToken).getSubject();
    }

    public boolean isTokenExpired(String encodedToken){
        return decodeToken(encodedToken).getExpiration().before(new Date());
    }

    private Claims decodeToken(String encodedToken){
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(encodedToken).getBody();
    }
}
