package mx.com.pandadevs.pibeapi.security;

// Java
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
// Spring
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
// JWT
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtAuth {

    @Value("${secret}")
    private String KEY;

    public String createToken(UserDetails userDetails){

        Claims roles = Jwts.claims();
        roles.put("roles",userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuer("PIBE-APP")
                .setClaims(roles)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, KEY)
                .compact();

    }

    public boolean validateToken(String token, UserDetails userDetails){
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
