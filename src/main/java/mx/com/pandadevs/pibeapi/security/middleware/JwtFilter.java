package mx.com.pandadevs.pibeapi.security.middleware;

import mx.com.pandadevs.pibeapi.models.auth.AuthDetailService;
import mx.com.pandadevs.pibeapi.security.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;
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
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuth jwtAuth;

    @Autowired
    private AuthDetailService authDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Getting authorization header
        String authHeader = request.getHeader("Authorization");

        //if there's nothing into header, throws unauthorized
        if (authHeader != null && authHeader.startsWith("Bearer")){

            //Getting raw token
            String encodedToken = authHeader.substring(7);
            String username =  jwtAuth.getUsername(encodedToken);

            //idk what the heck is securityContexHolder
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = authDetailService.loadUserByUsername(username);

                if(jwtAuth.validateToken(encodedToken, userDetails)){

                    UsernamePasswordAuthenticationToken token;
                    token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);

                }
            }
        }
        //just keep following flow
        filterChain.doFilter(request,response);
    }
}
