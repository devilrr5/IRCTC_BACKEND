package com.irctc.config;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.function.Function;
@Component
public class JwtUtil {
    @Value("${jwt.secret}") private String secret;
    @Value("${jwt.expirationMs}") private long expirationMs;
    public String generateToken(String username){ Date now=new Date(); Date exp=new Date(now.getTime()+expirationMs); return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact(); }
    public String getUsernameFromToken(String token){ return getClaimFromToken(token, Claims::getSubject); }
    public <T> T getClaimFromToken(String token, Function<Claims,T> resolver){ Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody(); return resolver.apply(claims); }
    public boolean isTokenExpired(String token){ Date exp = getClaimFromToken(token, Claims::getExpiration); return exp.before(new Date()); }
    public boolean validateToken(String token, String username){ return username.equals(getUsernameFromToken(token)) && !isTokenExpired(token); }
}
